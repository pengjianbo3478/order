package com.gl365.order.common.build;

import java.util.UUID;

import com.gl365.order.dto.mq.payment.model.PayReturn;
import com.gl365.order.model.OrderRefund;


public class OrderRefundBuild {

	
	/**
	 * 组装退款订单数据
	 *
	 * 
	 * @param source
	 * @return
	 *
	 * 		OrderRefund
	 */
	public static OrderRefund buildCreate(PayReturn source) {
		OrderRefund target = new OrderRefund();
		target.setMerchantNo(source.getMerchantNo());
		if(null!=source.getBeanAmount()){
			target.setBeanAmount(source.getBeanAmount());
		}
		if(null!=source.getCashAmount()){
			target.setCashAmount(source.getCashAmount());
		}
		if(null!=source.getCashAmount()){
			target.setGiftAmount(source.getGiftAmount());
		}
		target.setTotalAmount(source.getTotalAmount());
		target.setMemberId(source.getUserId());
		target.setMerchantNo(source.getMerchantNo());
		if(null!=source.getOperator()){
			target.setOperatorId(source.getOperator());
		}
		target.setOrderSn(source.getPayId());
		target.setOrigOrderSn(source.getOrigPayId());
		if(null!=source.getTerminal()&&!"".equals(source.getTerminal())){
			target.setTerminal(source.getTerminal());
		}
		//原单号
		target.setOrigOrderSn(source.getOrigPayId());
		target.setOrderId(UUID.randomUUID().toString().replace("-", ""));
		return target;
	}
	
	
}
