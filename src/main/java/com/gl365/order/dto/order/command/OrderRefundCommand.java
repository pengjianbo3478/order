package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModelProperty;

public class OrderRefundCommand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    /**
     * 退款单号
     */
    private String orderSn;
    /**
     * 退款单标题
     */
    private String orderTitle;

    /**
     * 单类型
     */
    private Integer orderType;
    /**
     * 单状态（5, "网上消费退货"|6, "网上消费部分退货"）
     */
    private Integer orderStatus;

    /**
     * 退款总金额
     */
    private BigDecimal totalAmount;

    /**
     * 退款豆子
     */
    private BigDecimal beanAmount;

    /**
     * 退款现金
     */
    private BigDecimal cashAmount;


    /**
     * 收款人id
     */
    private String memberId;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 退款原始单号
     */
    private String origOrderSn;



    private String terminal;
    
	@ApiModelProperty(value = "支付渠道")
    private String channel;



	public String getOrderSn() {
		return orderSn;
	}



	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}



	public String getOrderTitle() {
		return orderTitle;
	}



	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}



	public Integer getOrderType() {
		return orderType;
	}



	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}



	public Integer getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}



	public BigDecimal getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}



	public BigDecimal getBeanAmount() {
		return beanAmount;
	}



	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}



	public BigDecimal getCashAmount() {
		return cashAmount;
	}



	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getMerchantNo() {
		return merchantNo;
	}



	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}



	public String getOperatorId() {
		return operatorId;
	}



	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}



	public String getOrigOrderSn() {
		return origOrderSn;
	}



	public void setOrigOrderSn(String origOrderSn) {
		this.origOrderSn = origOrderSn;
	}



	public String getTerminal() {
		return terminal;
	}



	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}

}