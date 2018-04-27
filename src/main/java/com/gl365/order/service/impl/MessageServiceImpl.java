package com.gl365.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.model.Order;
import com.gl365.order.remote.MemberClient;
import com.gl365.order.remote.MerchantClient;
import com.gl365.order.remote.dto.CancelOrderDto;

@Component
public class MessageServiceImpl {
	private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);
	@Autowired
	private MemberClient memberClient;
	
	@Autowired
	private MerchantClient merchantClient;
	
	/**
	 * 退款中
	 * @param order
	 */
	public void sending(Order order){
		try{
			LOG.info("退单发送 getCancelOrderDto in {}",JsonUtils.toJsonString(order));
//			CancelOrderDto command=getCancelOrderDto(order);
//			LOG.info("退单发送 memberResult cancelordering command {}",JsonUtils.toJsonString(command));
//			ResultDto<?> memberResult=memberClient.cancelordering(command);
//			LOG.info("退单发送 memberResult cancelordering out {}",JsonUtils.toJsonString(memberResult));
		}catch(Exception e){
			LOG.error("退单发送异常",e);
		}
	}
	
	
	/**
	 * 退款完成
	 * @param order
	 */
	public void send(Order order){
		try{
			LOG.info("退单发送 getCancelOrderDto in {}",JsonUtils.toJsonString(order));
//			CancelOrderDto command=getCancelOrderDto(order);
//			
//			
//			LOG.info("<===cancelorder===>退单发送 memberResult param={}",JsonUtils.toJsonString(command));
//			ResultDto<?> memberResult=memberClient.cancelorder(command);
//			LOG.info("<===cancelorder===>退单发送 memberResult rlt={}",JsonUtils.toJsonString(memberResult));
//			
//			LOG.info("<===cancelorder===>退单发送 merchantClient param={}",JsonUtils.toJsonString(command));
//			ResultDto<?> merchantResult=merchantClient.cancelorder(command);
//			LOG.info("<===cancelorder===>退单发送 merchantResult rlt={}",JsonUtils.toJsonString(merchantResult));
		}catch(Exception e){
			LOG.error("退单发送异常",e);
		}
	}
	
	
	public 	CancelOrderDto getCancelOrderDto(Order order){
		

		
		CancelOrderDto cancel=new CancelOrderDto();
		cancel.setAlias(order.getMemberId());
		
		cancel.setGiftAmt(String.valueOf(order.getGiftAmount()));
		cancel.setBeanAmt(String.valueOf(order.getBeanAmount()));
		cancel.setCashAmt(String.valueOf(order.getCashAmount()));
		
		cancel.setTotalAmt(String.valueOf(order.getTotalAmount()));
		
		cancel.setMerchantNo(order.getMerchantNo());
		cancel.setOrderNo(order.getOrderSn());
		
		if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()){
			cancel.setTotalAmt(String.valueOf(order.getGroupMainuserPay()));
		}
		
	
		if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()||PaymentConfigEnum.COLLECTIVE_MINOR.getValue()==order.getPaymentConfig().intValue()){
			cancel.setOrderType("7");
		}else{
			cancel.setOrderType("1");
		}
		cancel.setOperator(order.getOperatorId());
		return cancel;
		
	}
	
}
