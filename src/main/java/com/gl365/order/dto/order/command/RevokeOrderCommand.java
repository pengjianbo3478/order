package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.enums.ChannelEnum;

import io.swagger.annotations.ApiModelProperty;

public class  RevokeOrderCommand implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 2024477179577543629L;


    /**
     * 订单编号
     */
    private String orderSn;
    
    /**
     * 支付场景
     */
    private Integer paymentConfig;
    
    /**
     * 付款人id
     */
    private String memberId;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Integer getPaymentConfig() {
		return paymentConfig;
	}

	public void setPaymentConfig(Integer paymentConfig) {
		this.paymentConfig = paymentConfig;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


    
}