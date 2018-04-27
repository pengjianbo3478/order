package com.gl365.order.remote.dto;

import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;


public class RefundInfoRsp extends RmResult{

	private static final long serialVersionUID = 5179100715909994504L;

	private String mchOrderNo;
	
	private String cpOrderNo;
	
	private String mchRefundNo;
	
	private String cpRefundNo;
	
	private BigDecimal amount; //交易金额 
	
	private BigDecimal decAmount; //抵扣金额
	
	private String transTime; //交易时间 yyyyMMddHHmmss
	
	private BigDecimal refundAmt; //退款金额
	
	private String refundTime; //退款时间 yyyyMMddHHmmss
	
	private String refundResult; //状态:0--未退款;1--退款成功;2--退款处理中;3--退款失败

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public String getCpOrderNo() {
		return cpOrderNo;
	}

	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}

	public String getMchRefundNo() {
		return mchRefundNo;
	}

	public void setMchRefundNo(String mchRefundNo) {
		this.mchRefundNo = mchRefundNo;
	}

	public String getCpRefundNo() {
		return cpRefundNo;
	}

	public void setCpRefundNo(String cpRefundNo) {
		this.cpRefundNo = cpRefundNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDecAmount() {
		return decAmount;
	}

	public void setDecAmount(BigDecimal decAmount) {
		this.decAmount = decAmount;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
	}
	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
}
