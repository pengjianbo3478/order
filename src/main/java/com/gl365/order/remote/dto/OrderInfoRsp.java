package com.gl365.order.remote.dto;

import java.math.BigDecimal;

public class OrderInfoRsp extends RmResult {
	private static final long serialVersionUID = -3556008185962531966L;

	// 平台订单号
	private String cpOrderNo;

	// 商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
	private String mchOrderNo;

	// 预支付 ID，用于后续支付时使用
	private String tokenId;

	private String payInfo;
	
	//返回乐豆
	private BigDecimal rebatePoints;

	//支付现金
	private BigDecimal payCash;

	//支付乐豆
	private BigDecimal payPoints;
	
    //付款银行
    private String bankType;
    
    //抵扣结果
    private String decResult;
    
    //抵扣金额
    private BigDecimal decAmount;
    
    //融脉订单号
    private String organOrderNo;

    //通道订单号
    private String transactionId;
    

	public String getCpOrderNo() {
		return cpOrderNo;
	}

	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public BigDecimal getRebatePoints() {
		return rebatePoints;
	}

	public void setRebatePoints(BigDecimal rebatePoints) {
		this.rebatePoints = rebatePoints;
	}

	public BigDecimal getPayCash() {
		return payCash;
	}

	public void setPayCash(BigDecimal payCash) {
		this.payCash = payCash;
	}

	public BigDecimal getPayPoints() {
		return payPoints;
	}

	public void setPayPoints(BigDecimal payPoints) {
		this.payPoints = payPoints;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getDecResult() {
		return decResult;
	}

	public void setDecResult(String decResult) {
		this.decResult = decResult;
	}

	public BigDecimal getDecAmount() {
		return decAmount;
	}

	public void setDecAmount(BigDecimal decAmount) {
		this.decAmount = decAmount;
	}

	public String getOrganOrderNo() {
		return organOrderNo;
	}

	public void setOrganOrderNo(String organOrderNo) {
		this.organOrderNo = organOrderNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
