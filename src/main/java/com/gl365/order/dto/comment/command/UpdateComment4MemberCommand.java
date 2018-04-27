package com.gl365.order.dto.comment.command;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateComment4MemberCommand {
	/**
	 * true delete : 0 ; false recover : 1
	 */
	private boolean isDelete;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 商户编号
	 */
	private String merchantNo;

	/**
	 * 支付订单编号
	 */
	private String paymentNo;

	/**
	 * 评分
	 */
	private Integer grade;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 评论时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDateTime;

	/**
	 * 商家评论标签
	 */
	private Integer[] labels;

	public UpdateComment4MemberCommand() {
		super();
	}

	public UpdateComment4MemberCommand(String userId, String merchantNo, String paymentNo) {
		super();
		this.userId = userId;
		this.merchantNo = merchantNo;
		this.paymentNo = paymentNo;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer[] getLabels() {
		return labels;
	}

	public void setLabels(Integer[] labels) {
		this.labels = labels;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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

}
