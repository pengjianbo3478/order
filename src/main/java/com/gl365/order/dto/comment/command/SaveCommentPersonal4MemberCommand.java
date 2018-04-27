package com.gl365.order.dto.comment.command;

import java.math.BigDecimal;

public class SaveCommentPersonal4MemberCommand {

	private String userId; // 用户ID

	private String paymentNo;// 原付费通支付订单号

	private String tipPaymentNo;// 现付费通支付订单号

	private String merchantNo;// 商家编号

	private String operatorId;// 评论(打赏)对象用户ID

	private BigDecimal tip;// 打赏金额(消费)
	
	private BigDecimal beanTip;

	private BigDecimal grade;// 评分

	private Integer status;// 打赏状态

	/**
	 * true delete : 0 ; false recover : 1
	 */
	private boolean isDelete;

	public SaveCommentPersonal4MemberCommand() {
		super();
	}

	public SaveCommentPersonal4MemberCommand(String userId, String merchantNo, String paymentNo) {
		super();
		this.userId = userId;
		this.merchantNo = merchantNo;
		this.tipPaymentNo = paymentNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTipPaymentNo() {
		return tipPaymentNo;
	}

	public void setTipPaymentNo(String tipPaymentNo) {
		this.tipPaymentNo = tipPaymentNo;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
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

	public BigDecimal getTip() {
		return tip;
	}

	public void setTip(BigDecimal tip) {
		this.tip = tip;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public BigDecimal getBeanTip() {
		return beanTip;
	}

	public void setBeanTip(BigDecimal beanTip) {
		this.beanTip = beanTip;
	}

}
