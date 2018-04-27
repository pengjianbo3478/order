package com.gl365.order.common.build;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.dto.mq.payment.model.PayMain;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.command.CreateGroupOrderCommand;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.dto.order.command.GetOrderSn4OrderCommand;
import com.gl365.order.dto.order.command.UpdateOrderCommand;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.model.Order;


public class OrderBuild {

	
	/**
	 * 组装订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	public static Order buildCreateFFT(CreateOrderCommand source) {
		Order target = new Order();
		target.setPaymentConfig(source.getPaymentConfig());
		target.setMemberId(source.getMemberId());
		target.setMerchantNo(source.getMerchantNo());
		//下单直接变成付款中
		target.setOrderStatus(OrderStatusEnum.NON_PAYMENT.getValue());
		//付款中
		//target.setOrderStatus(OrderStatusEnum.PROCESS_PAYMENT.getValue());
		if(null!=source.getTerminal()&&!"".equals(source.getTerminal())){
			target.setTerminal(source.getTerminal());
		}
//		if(null!=source.getBarCode()&&!"".equals(source.getBarCode())){
//			target.setBarCode(source.getBarCode());
//		}
		if(null!=source.getOperatorId()&&!"".equals(source.getOperatorId())){
			target.setOperatorId(source.getOperatorId());
		}
		if(null!=source.getRewardUserId()&&!"".equals(source.getRewardUserId())){
			target.setRewardUserId(source.getRewardUserId());
		}
		if(null!=source.getOrderTitle()&&!"".equals(source.getOrderTitle())){
			target.setOrderTitle(source.getOrderTitle());
		}
		target.setOrderId(UUID.randomUUID().toString().replace("-", ""));
		target.setCreateTime(LocalDateTime.now());
		if(null!=source.getAloneAmount()){
			target.setAloneAmount(source.getAloneAmount());
		}
		if(null!=source.getGroupId()){
			target.setGroupId(source.getGroupId());
		}
		if(null!=source.getTotalAmount()){
			target.setTotalAmount(source.getTotalAmount());
		}
		if(null!=source.getChannel()){
			target.setChannel(source.getChannel());
		}

		return target;
	}
	
	
	
	/**
	 * 组装订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	public static Order buildCreate(CreateOrderCommand source) {
		Order target = new Order();
		target.setPaymentConfig(source.getPaymentConfig());
		target.setMemberId(source.getMemberId());
		target.setMerchantNo(source.getMerchantNo());
		//下单直接变成付款中
		//target.setOrderStatus(OrderStatusEnum.NON_PAYMENT.getValue());
		//付款中
		target.setOrderStatus(OrderStatusEnum.PROCESS_PAYMENT.getValue());
		if(null!=source.getTerminal()&&!"".equals(source.getTerminal())){
			target.setTerminal(source.getTerminal());
		}
//		if(null!=source.getBarCode()&&!"".equals(source.getBarCode())){
//			target.setBarCode(source.getBarCode());
//		}
		if(null!=source.getOperatorId()&&!"".equals(source.getOperatorId())){
			target.setOperatorId(source.getOperatorId());
		}
		if(null!=source.getRewardUserId()&&!"".equals(source.getRewardUserId())){
			target.setRewardUserId(source.getRewardUserId());
		}
		if(null!=source.getOrderTitle()&&!"".equals(source.getOrderTitle())){
			target.setOrderTitle(source.getOrderTitle());
		}
		target.setOrderId(UUID.randomUUID().toString().replace("-", ""));
		target.setCreateTime(LocalDateTime.now());
		if(null!=source.getAloneAmount()){
			target.setAloneAmount(source.getAloneAmount());
		}
		if(null!=source.getGroupId()){
			target.setGroupId(source.getGroupId());
		}
		if(null!=source.getTotalAmount()){
			target.setTotalAmount(source.getTotalAmount());
		}
		if(null!=source.getChannel()){
			target.setChannel(source.getChannel());
		}
		if(null!=source.getMchCreateIp()){
			target.setMchCreateIp(source.getMchCreateIp());
		}
		if(null!=source.getNoBenefitAmount()){
			target.setNoBenefitAmount(source.getNoBenefitAmount());
		}

		return target;
	}
	
	
	
	/**
	 * 修改组装订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	public static Order buildUpdate(UpdateOrderCommand source) {
		Order target = new Order();
		BeanUtils.copyProperties(source, target);
		target.setModifyTime(LocalDateTime.now());
		return target;
	}
	
	/**
	 * 组装查询订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	public static Map<String,Object>  buildGetOrderSn(GetOrderSn4OrderCommand source) {
		Map<String,Object> target = new HashMap<String,Object> ();
		if(null!=source.getFftOrderSn()&&!"".equals(source.getFftOrderSn())){
			target.put("fftOrderSn", source.getFftOrderSn());
		}
		if(null!=source.getFftSequence()&&!"".equals(source.getFftSequence())){
			target.put("fftSequence", source.getFftSequence());
		}
		if(null!=source.getOrderSn()&&!"".equals(source.getOrderSn())){
			target.put("orderSn", source.getOrderSn());
		}
		return target;
	}
	
	
	/**
	 * 组装查询订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	public static Order  buildPaymentMQ4Order(PaymentMQ  source) {
		Order target = new Order ();
		PayMain payMain=source.getPaymentBody().getPayMain();
		target.setMerchantNo(payMain.getMerchantNo());
		target.setMemberId(payMain.getUserId());
		target.setTotalAmount(payMain.getTotalAmount());
		target.setOrderSn(payMain.getMerchantOrderNo());
		if(null!=payMain.getBeanAmount()){
			target.setBeanAmount(payMain.getBeanAmount());
		}
		if(null!=payMain.getCashAmount()){
			target.setCashAmount(payMain.getCashAmount());
		}
		if(null!=payMain.getOperator()){
			target.setOperatorId(payMain.getOperator());
		}
		/**
		 * 被打赏员工 String 32 否
		 */
		if(null!=payMain.getRewardUserId()){
			target.setRewardUserId(payMain.getRewardUserId());
		}
		/**
		 * 被打赏原支付单号 String 32 否
		 */
		if(null!=payMain.getRewardPayId()){
			target.setOrigOrderSn(payMain.getRewardPayId());
		}
		/**
		 * 终端
		 */
		if(null!=payMain.getTerminal()){
			target.setTerminal(payMain.getTerminal());
		}
		if(null!=payMain.getMerchantOrderTitle()){
			target.setOrderTitle(payMain.getMerchantOrderTitle());
		}
		/**
		 * 返利金额
		 * 
		 * @param giftAmount
		 */
		
		
		
		if(null!=payMain.getGiftAmount()){
			target.setGiftAmount(payMain.getGiftAmount());
		}
		//群买单类型
		if(payMain.getOrderType().equals("7")){
			if(null!=payMain.getGroupGiftAmount()){
				target.setGiftAmount(payMain.getGroupGiftAmount());
			}
		}
		
		/**
		 * 支付公司流水号
		 */
		if(null!=payMain.getRequestId()){
			target.setFftSequence(payMain.getRequestId());
		}
		/**
		 * 支付公司订单号
		 */
		if(null!=payMain.getOrganOrderNo()){
			target.setFftOrderSn(payMain.getOrganOrderNo());
		}
		/**
		 * 订单类型
		 */
		target.setOrderType(Integer.parseInt(payMain.getOrderType().trim()));
		target.setModifyTime(LocalDateTime.now());
		target.setPushTime(LocalDateTime.now());
		return target;
	}
	
	/**
	 * 
	 * @Title: buildOrderDto
	 * @Description: 查询数据转换
	 * @param source
	 * @return
	 * @return: OrderDto
	 */
	public static OrderDto buildOrderDto(Order source) {
		OrderDto target = new OrderDto();
		// TODO 后面有频繁转换的业务时再用setter/getter
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	
	/**
	 * 组装订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		Order
	 */
	/**
	 * @param source
	 * @return
	 */
	public static Order buildCreate(CreateGroupOrderCommand source) {
		Order target = new Order();

		
		target.setPaymentConfig(source.getPaymentConfig());
		if(null!=source.getMemberId()&&!"".equals(source.getMemberId())){
			target.setMemberId(source.getMemberId());
		}
		
		if(null!=source.getMerchantNo()&&!"".equals(source.getMerchantNo())){
			target.setMerchantNo(source.getMerchantNo());
		}
		
		
		//下单直接变成付款中
		target.setOrderStatus(OrderStatusEnum.NON_PAYMENT.getValue());
		if(null!=source.getOperatorId()&&!"".equals(source.getOperatorId())){
			target.setOperatorId(source.getOperatorId());
		}
		if(null!=source.getOrderTitle()&&!"".equals(source.getOrderTitle())){
			target.setOrderTitle(source.getOrderTitle());
		}
		target.setOrderId(UUID.randomUUID().toString().replace("-", ""));
		target.setCreateTime(LocalDateTime.now());
		if(null!=source.getAloneAmount()){
			target.setAloneAmount(source.getAloneAmount());
		}
		if(null!=source.getGroupId()){
			target.setGroupId(source.getGroupId());
		}
		if(null!=source.getTotalAmount()){
			target.setTotalAmount(source.getTotalAmount());
		}
		if(null!=source.getChannel()){
			target.setChannel(source.getChannel());
		}
		if(null!=source.getAloneAmount()){
			target.setAloneAmount(source.getAloneAmount());
		}
		if(null!=source.getGroupMainuserPay()){
			target.setGroupMainuserPay(source.getGroupMainuserPay());
		}
		if(null!=source.getTerminal()){
			target.setTerminal(source.getTerminal());
		}
		if(null!=source.getNoBenefitAmount()){
			target.setNoBenefitAmount(source.getNoBenefitAmount());
		}
		//群买单设置单号
		if(source.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MAIN.getValue()||
				source.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MINOR.getValue()	){
			if(null!=source.getOrderSn()&&!"".equals(source.getOrderSn())){
				target.setOrderSn(source.getOrderSn());
			}
		}
		



		return target;
	}
}
