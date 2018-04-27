package com.gl365.order.mq.consumer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.aliyun.ons.OnsListener;
import com.gl365.order.common.PayMentErrorCode;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.mq.wx.PayResultBo;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.CfrontService;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.dto.GroupPayDistributeReq;
import com.gl365.order.remote.dto.WxConfirmReqDTO;
import com.gl365.order.remote.dto.WxConfirmRespDTO;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;
import com.google.gson.Gson;
/**
 * 交易成功结果通知
 * @author DELL
 *
 */
@Component("wxpay-gateway-pay-consumer")
public class wxConsumer implements OnsListener {

	private static final Logger LOG = LoggerFactory.getLogger(wxConsumer.class);


	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private CfrontService cfrontService;
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;

	@Override
	public void receive(byte[] source) {
		String message = new String(source);
		LOG.info("<1 mq-received>订单接收网关交易成功消费信息,message={}", message);
		
		
		try {
			Order record=new Order();
			PayResultBo payment = newInstance(message);
			
			
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("orderSn",payment.getMerchantOrderNo());
			Order dbOrder=orderMapper.selectByOrderSn(map);
			LOG.info("<1.1 mq-received>Mq 查询DB订单,message={}", JsonUtil.toJsonString(dbOrder));
			if(null==dbOrder||null==dbOrder.getOrderSn()){
				LOG.info("<2 mq-received>Mq 消息订单不存在,message={}", JsonUtil.toJsonString(map));
				return ;
			}
			if(dbOrder.getOrderStatus()==OrderStatusEnum.COMPLETE_PAYMENT.getValue()||
					dbOrder.getOrderStatus()==OrderStatusEnum.PAY_FAIL.getValue()||
							dbOrder.getOrderStatus()==OrderStatusEnum.ALL_REFUND.getValue()||
									dbOrder.getOrderStatus()==OrderStatusEnum.TIME_REFUND.getValue()
					){
				LOG.info("<3 mq-received>Mq 消息订单已经处理完成,message={}", JsonUtil.toJsonString(map));
				return ;
			}
			
			
			//按修改行数判断
			LOG.info("4 收到MQ修改订单状态message={}", JsonUtil.toJsonString(record));
			int updateRow =orderMapper.updateOrderStatus(payment.getMerchantOrderNo(), OrderStatusEnum.COMPLETE_PAYMENT.getValue(),dbOrder.getOrderStatus() );
			LOG.info("5 收到MQ修改订单状态影响行message={}", JsonUtil.toJsonString(updateRow));
			//修改失败返回
			if(updateRow<=0){
				LOG.info("6 收到MQ修改订单状态影响行为0直接返回！！");
				return ;
			}
			
			
			WxConfirmReqDTO wxc= new WxConfirmReqDTO();
			wxc.setBankType(payment.getBankType());//目前没有数据
			wxc.setPayDesc(payment.getPayDesc());//目前没有数据
			wxc.setCashAmount(payment.getCashAmount());
			wxc.setDecAmount(payment.getDecAmount());
			wxc.setDecResult(payment.getDecResult());
			wxc.setMerchantOrderNo(payment.getMerchantOrderNo());
			wxc.setOrganCode(payment.getOrganCode());
			wxc.setOrganOrderNo(payment.getOrganOrderNo());
			wxc.setOrganPayTime(str2localDateTime(payment.getOrganPayTime()));
			
			LOG.info("7  调用cfrontService begin,PayResult={},OrderSn={},dbOrder={}", payment.getPayResult(),dbOrder.getOrderSn(), JsonUtil.toJsonString(dbOrder));
			if(payment.getPayResult().equals("0")){//已支付
				wxc.setPayResult("0");
				record.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				callCfront( dbOrder);
			}else{//未支付
				wxc.setPayResult("1");
				record.setOrderStatus(OrderStatusEnum.PAY_FAIL.getValue());
			}
			wxc.setTransactionId(payment.getTransactionId());
			LOG.info("<8 mq-received>抵扣乐豆,message={}", JsonUtil.toJsonString(wxc));
			WxConfirmRespDTO wxrt = null;
			try{
				wxrt=paymentClient.confirm(wxc);
			}catch(Exception e){
				LOG.error("9调用交易系统抵扣乐豆 paymentClient.confirm 异常,e:",e);
			}
			LOG.info("<10 mq-received>抵扣乐豆返回,message={}单号：{}", JsonUtil.toJsonString(wxrt),payment.getMerchantOrderNo());
			if(wxrt == null){
				record.setBeanType(BeanStatusEnum.bean_init.getValue());
			}else{
				//判断抵扣乐豆是否成功
				if(wxrt.getResultCode().equals(ResultCodeEnum.System.SUCCESS.getCode())){
					record.setBeanType(BeanStatusEnum.bean_success.getValue());
				}else if(PayMentErrorCode.map.containsKey(wxrt.getResultCode())){
					record.setBeanType(BeanStatusEnum.bean_fail.getValue());
				}else{
					record.setBeanType(BeanStatusEnum.bean_init.getValue());
				}
			}
			
			record.setOrderSn(payment.getMerchantOrderNo());
			record.setTransactionId(payment.getTransactionId());
			record.setDecAmount(payment.getDecAmount());
			record.setDecResult(payment.getDecResult());
			orderMapper.updateByOrderSnSelective(record);
			LOG.info("<11 mq-received>修改订单状态完成,message={}", JsonUtil.toJsonString(record));
			
			//乐豆抵扣失败发起退款
			if(BeanStatusEnum.bean_fail.getValue() == record.getBeanType()){
				OrderRefundCommand command = new OrderRefundCommand();
				command.setOrigOrderSn(record.getOrderSn());
				command.setTotalAmount(dbOrder.getCashAmount());
				refundOrderService.refundOrder(command);
			}else{
				//发送通知到微信公众号
				Order order=orderMapper.selectByOrderSn(map);
				order.setPaymentTime(str2localDateTime(payment.getOrganPayTime()));
				orderNotifyProducer.sendConfirm(order);
				LOG.info("<11.1 mq-received>发送消息到微信端,message={}", JsonUtil.toJsonString(order));
			}
		} catch (Exception e) {
			LOG.error("<12 mq-received>订单接收网关成功消费信息异常	===>	RedenvelopeConsumer.receive exception,e:{}", e);
			throw new RuntimeException(e);
		}
	}
	
	
	public void callCfront(Order dbOrder){
		try{
			GroupPayDistributeReq req=new GroupPayDistributeReq();
			req.setGroupId(dbOrder.getGroupId());
			req.setUserId(dbOrder.getMemberId());
			LOG.info("调用cfrontService,message={}", JsonUtil.toJsonString(req));
			ResultDto<?> rlt = cfrontService.cancel(req);
			LOG.info("调用cfrontService,rlt={}", JsonUtil.toJsonString(rlt));
		}catch(Exception e){
			LOG.error("调用cfrontService异常 ,e:{}", e);
		}
		
	}
	
	private LocalDateTime str2localDateTime(String organPayTime){
		try{
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(organPayTime,df);
			return ldt;
		}catch(Exception e){
			LOG.warn("日期类型不匹配转换错误,organPayTime={} 错误：{}", JsonUtil.toJsonString(organPayTime),e);	
			return LocalDateTime.now();
		}
		
	}

	private PayResultBo newInstance(String message) {
		return new Gson().fromJson(message, PayResultBo.class);
	}

}
