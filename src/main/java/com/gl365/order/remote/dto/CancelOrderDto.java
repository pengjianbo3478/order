package com.gl365.order.remote.dto;

import io.swagger.annotations.ApiModelProperty;

public class CancelOrderDto {

	@ApiModelProperty("推送对象,不可为空")
	private String alias;
	
	@ApiModelProperty("当前支付订单")
	private String payId;
	
	@ApiModelProperty("原交易订单号")
	private String origPayId;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("商户名称")
	private String merchantName;
	
	@ApiModelProperty("商户编号")
	private String merchantNo;
	
	@ApiModelProperty("个人支付总金额")
	private String totalAmt;
	
	@ApiModelProperty("个人现金支付")
	private String cashAmt; 
	
	@ApiModelProperty("个人扣豆")
	private String beanAmt; 
	
	@ApiModelProperty("个人返豆")
	private String giftAmt; 
	
	@ApiModelProperty("用户订单号")
	private String billno; 
	
	@ApiModelProperty("订单类型1：正常订单（默认为1）2：打赏订单3：达人订单4:网购订单7:群买单")
	private String orderType;
	
	@ApiModelProperty("给乐订单号")
	private String orderNo; 
	
	@ApiModelProperty("操作员id")
	private String operator; 

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getOrigPayId() {
		return origPayId;
	}

	public void setOrigPayId(String origPayId) {
		this.origPayId = origPayId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}

	public String getBeanAmt() {
		return beanAmt;
	}

	public void setBeanAmt(String beanAmt) {
		this.beanAmt = beanAmt;
	}

	public String getGiftAmt() {
		return giftAmt;
	}

	public void setGiftAmt(String giftAmt) {
		this.giftAmt = giftAmt;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
