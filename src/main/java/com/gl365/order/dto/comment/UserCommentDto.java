package com.gl365.order.dto.comment;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserCommentDto {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 商户简称
	 */
	private String ShortName;

	/**
	 * 评分 0~5
	 */
	private Integer grade;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 支付订单编号
	 */
	private String paymentNo;

	/**
	 * 商户编号
	 */
	private String merchantNo;

	/**
	 * 商户图片
	 */
	private String merchantImage;

	/**
	 * 商户商圈名称
	 */
	private String merchantAreaName;

	/**
	 * 商户行业名称
	 */
	private String merchantcategoryName;

	/**
	 * 商家评论标签
	 */
	private String[] labels;

	/**
	 * 评论时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getMerchantImage() {
		return merchantImage;
	}

	public void setMerchantImage(String merchantImage) {
		this.merchantImage = merchantImage;
	}

	public String getMerchantAreaName() {
		return merchantAreaName;
	}

	public void setMerchantAreaName(String merchantAreaName) {
		this.merchantAreaName = merchantAreaName;
	}

	public String getMerchantcategoryName() {
		return merchantcategoryName;
	}

	public void setMerchantcategoryName(String merchantcategoryName) {
		this.merchantcategoryName = merchantcategoryName;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	

}