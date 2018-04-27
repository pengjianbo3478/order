package com.gl365.order.timer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.job.core.biz.model.ReturnT;
import com.gl365.job.core.handler.IJobHandler;
import com.gl365.job.core.handler.annotation.JobHander;
import com.gl365.job.core.log.JobLogger;
import com.gl365.order.common.PayMentErrorCode;
import com.gl365.order.common.Properties;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.OrderQueryReq;
import com.gl365.order.remote.dto.QueryOrderDetail;
import com.gl365.order.remote.dto.ResultDto;
import com.gl365.order.remote.dto.WxConfirmReqDTO;
import com.gl365.order.remote.dto.WxConfirmRespDTO;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;

@JobHander(value = "payOrderJobHandler")
@Service
public class PayOrderJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("=======>JOB, getOrderByQuerySum.>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>");
		getOrderByQuerySum();
		
		JobLogger.log("=======>JOB, getOrderByQuerySum.>>>>>>>>>>>>>>end>>>>>>>>>>>>>>");
		
		
		return ReturnT.SUCCESS;
	}

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	/**
	 * 支付中状态去查询订单状态
	 */
	public void getOrderByQuerySum(){
		JobLogger.log("轮询查询订单-------开始--->");
		Order record=new Order();
		record.setQuerySum(properties.getQuerySum());
		record.setQueryTime(LocalDateTime.now());
		//查询待同步的订单
		List<Order> list=orderMapper.getOrderByQuerySum(record);
		JobLogger.log("轮询查询订单-------list--->{0}",JsonUtils.toJsonString(list));
		for(Order order:list){
			
			OrderQueryReq req=new OrderQueryReq();
			req.setMchOrderNo(order.getOrderSn());
			JobLogger.log("轮询查询订单------- 单号--->{0}",order.getOrderSn());
			ResultDto<QueryOrderDetail> result= wxPayGatewayService.doAction(req);
			
			
			JobLogger.log("轮询查询订单------- 结果--->{0} 单号{1}",JsonUtils.toJsonString(result),order.getOrderSn());
			
			Integer orderStatus= getOrderStatus(result.getResult().getPayResult());
			JobLogger.log("轮询查询订单------- 状态--->{0}",orderStatus);
			//1成功其他失败
			if(orderStatus.intValue()==OrderStatusEnum.COMPLETE_PAYMENT.getValue()){
				
				
				
				
				//按修改行数判断
				JobLogger.log("轮询查询订单 收到MQ修改订单状态message={0}", JsonUtil.toJsonString(record));
				int updateRow =orderMapper.updateOrderStatus(order.getOrderSn(), OrderStatusEnum.COMPLETE_PAYMENT.getValue(),order.getOrderStatus() );
				JobLogger.log("轮询查询订单 收到MQ修改订单状态影响行message={0}", JsonUtil.toJsonString(updateRow));
				//修改失败返回
				if(updateRow<=0){
					JobLogger.log("轮询查询订单 收到MQ修改订单状态影响行为0直接返回！！");
					return ;
				}
				
				
				
				Order recordUpdate=new Order();
				
				//TODO已支付不能再次调用
				
				WxConfirmReqDTO wxc= new WxConfirmReqDTO();
				wxc.setCashAmount(order.getCashAmount());
				BigDecimal dec=new BigDecimal(result.getResult().getDecAmount());
				wxc.setDecAmount(dec);
				wxc.setDecResult("1");
				wxc.setMerchantOrderNo(order.getOrderSn());
				wxc.setOrganCode("10003");
				wxc.setOrganOrderNo(result.getResult().getCpOrderNo());
				wxc.setOrganPayTime(LocalDateTime.now());
				wxc.setPayResult("0");
				
				wxc.setTransactionId(result.getResult().getPayOrderNo());
				JobLogger.log("轮询查询订单<mq-received>抵扣乐豆,message={0}", JsonUtil.toJsonString(wxc));
				WxConfirmRespDTO wx=paymentClient.confirm(wxc);
				JobLogger.log("轮询查询订单<mq-received>抵扣乐豆返回,message={0} 单号：{}", JsonUtil.toJsonString(wx),order.getOrderSn());
				
				if(wx.getResultCode().equals(ResultCodeEnum.System.SUCCESS.getCode())){
					recordUpdate.setBeanType(BeanStatusEnum.bean_success.getValue());
				}else if(PayMentErrorCode.map.containsKey(wx.getResultCode())){
					recordUpdate.setBeanType(BeanStatusEnum.bean_fail.getValue());
				}else{
					recordUpdate.setBeanType(BeanStatusEnum.bean_init.getValue());
				}
				recordUpdate.setOrderSn(order.getOrderSn());
				recordUpdate.setTransactionId(result.getResult().getPayOrderNo());
				recordUpdate.setDecAmount(dec);
				recordUpdate.setDecResult("1");
				recordUpdate.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				orderMapper.updateByOrderSnSelective(recordUpdate);
				
				
				if(BeanStatusEnum.bean_fail.getValue() == recordUpdate.getBeanType()){
					OrderRefundCommand command = new OrderRefundCommand();
					command.setOrigOrderSn(recordUpdate.getOrderSn());
					command.setTotalAmount(recordUpdate.getCashAmount());
					com.gl365.order.dto.ResultDto<?> refund= refundOrderService.refundOrder(command);
					JobLogger.log("<轮询 confirm-received>退款单返回结果,message={}", JsonUtil.toJsonString(refund));
				}else{
					//发送通知到微信公众号
					Order orderNotify=orderMapper.selectBySn(recordUpdate.getOrderSn());
					orderNotify.setPaymentTime(str2localDateTime(result.getResult().getTransTime()));
					orderNotifyProducer.sendConfirm(orderNotify);
				}
				
				
			}else if(orderStatus.intValue()==OrderStatusEnum.NON_PAYMENT.getValue()){//未付款
				order.setQuerySum(order.getQuerySum()+1);
				int i=3*order.getQuerySum();
				JobLogger.log("轮询查询订单-------时间步长--->{0}",i);
				order.setQueryTime(order.getQueryTime().plusMinutes(Long.valueOf(i)));
				JobLogger.log("轮询查询订单-------order.getQuerySum()--->{0}",order.getQuerySum());
				
				orderMapper.updateQuerySumByOrderSn(order);
			}else if(orderStatus.intValue()==OrderStatusEnum.PAY_FAIL.getValue()){//支付失败
				Order recordUpdate=new Order();
				recordUpdate.setOrderSn(order.getOrderSn());
				recordUpdate.setOrderStatus(OrderStatusEnum.PAY_FAIL.getValue());
				orderMapper.updateByOrderSnSelective(order);
			}
			JobLogger.log("轮询查询订单------- 修改完成--->{0}",JsonUtils.toJsonString(order));
		}
		
	}
	
	private Integer getOrderStatus(String payResult){
		
		Integer orderStatus=OrderStatusEnum.NON_DATA.getValue();;
		
		if(payResult.equals("0")){//0：未支付
			orderStatus=OrderStatusEnum.NON_PAYMENT.getValue();
		}else if(payResult.equals("1")){//1：已支付
			orderStatus=OrderStatusEnum.COMPLETE_PAYMENT.getValue();
		}else if(payResult.equals("2")){//2：退款处理中
			orderStatus=OrderStatusEnum.TIME_REFUND.getValue();
		}else if(payResult.equals("3")){//3：已冲正
			orderStatus=OrderStatusEnum.REVERSAL_PAYMENT.getValue();
		}else if(payResult.equals("4")){//4：已撤销
			orderStatus=OrderStatusEnum.REVOKE_PAYMENT.getValue();
		}else if(payResult.equals("5")){//5：支付失败
			orderStatus=OrderStatusEnum.PAY_FAIL.getValue();
		}else if(payResult.equals("7")){//7：已退款
			orderStatus=OrderStatusEnum.ALL_REFUND.getValue();
		}
		return orderStatus;
	}
	
	private LocalDateTime str2localDateTime(String organPayTime){
		try{
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(organPayTime,df);
			return ldt;
		}catch(Exception e){
			JobLogger.log("日期类型不匹配转换错误,organPayTime={} 错误：{}", JsonUtil.toJsonString(organPayTime),e);	
			return LocalDateTime.now();
		}
		
	}
}  