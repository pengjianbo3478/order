package com.gl365.order.timer;

import java.time.LocalDateTime;
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
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.dto.WxConfirmReqDTO;
import com.gl365.order.remote.dto.WxConfirmRespDTO;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;

@JobHander(value = "confirmOrderJobHandler")
@Service
public class ConfirmOrderJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("=======>JOB, confirmOrderJobHandler.>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>");
		queryOredrBeanFail();
		JobLogger.log("=======>JOB, confirmOrderJobHandler.>>>>>>>>>>>>>>end>>>>>>>>>>>>>>");
		
		
		return ReturnT.SUCCESS;
	}

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private PaymentClient paymentClient;
	
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;
	
	@Autowired
	private RefundOrderService refundOrderService;
	/**
	 * 交易完成,抵扣乐豆未知,抵扣完成跳过,抵扣失败退款.
	 */
	public void queryOredrBeanFail(){
		
		List<Order> list=orderMapper.getOrderByBeanQuerySum(properties.getQuerySum());
		
		for(Order order:list){
			
			if(order.getBeanType().intValue()==BeanStatusEnum.bean_init.getValue()){
			Order recordUpdate=new Order();
			WxConfirmReqDTO wxc= new WxConfirmReqDTO();
			wxc.setCashAmount(order.getCashAmount());
			wxc.setDecAmount(order.getDecAmount());
			wxc.setDecResult("1");
			wxc.setMerchantOrderNo(order.getOrderSn());
			wxc.setOrganCode("10003");
			wxc.setOrganOrderNo(order.getRmOrderNo());
			wxc.setOrganPayTime(LocalDateTime.now());
			wxc.setPayResult("0");
			
			wxc.setTransactionId(order.getTransactionId());
			JobLogger.log("<mq-received>抵扣乐豆,message={0}", JsonUtil.toJsonString(wxc));
			
			WxConfirmRespDTO wxrt = null;
			try{
				wxrt=paymentClient.confirm(wxc);
			}catch(Exception e){
				JobLogger.log("调用交易系统抵扣乐豆 paymentClient.confirm 异常,e:",e);
			}
			JobLogger.log("轮询扣豆<mq-received>抵扣乐豆返回,message={0},单号：{1}", JsonUtil.toJsonString(wxrt),order.getOrderSn());
			if(wxrt == null){
				recordUpdate.setBeanType(BeanStatusEnum.bean_init.getValue());
			}else{
				//判断抵扣乐豆是否成功
				if(wxrt.getResultCode().equals(ResultCodeEnum.System.SUCCESS.getCode())){
					recordUpdate.setBeanType(BeanStatusEnum.bean_success.getValue());
				}else if(PayMentErrorCode.map.containsKey(wxrt.getResultCode())){
					recordUpdate.setBeanType(BeanStatusEnum.bean_fail.getValue());
				}else{
					recordUpdate.setBeanType(BeanStatusEnum.bean_init.getValue());
				}
			}
			recordUpdate.setOrderSn(order.getOrderSn());
			orderMapper.updateByOrderSnSelective(recordUpdate);
			
			//乐豆抵扣失败发起退款
			if(BeanStatusEnum.bean_fail.getValue() == recordUpdate.getBeanType()){
				OrderRefundCommand command = new OrderRefundCommand();
				command.setOrigOrderSn(order.getOrderSn());
				command.setTotalAmount(order.getCashAmount());
				refundOrderService.refundOrder(command);
			}else{
				//发送通知到微信公众号
				Order orderNotify=orderMapper.selectBySn(recordUpdate.getOrderSn());
				orderNotify.setPaymentTime(orderNotify.getCreateTime());
				orderNotifyProducer.sendConfirm(orderNotify);
			}
			
			}
			
		}
		
	}
}  