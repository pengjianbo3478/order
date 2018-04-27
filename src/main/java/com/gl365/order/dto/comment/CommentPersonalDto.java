package com.gl365.order.dto.comment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentPersonalDto implements Serializable {

	private static final long serialVersionUID = 7582193025667041347L;

	private String id;

	private String userId;

	private String operatorId;

	/**
	 * 操作员名称
	 */
	private String operatorName;

	/**
	 * 操作员头像
	 */
	private String operatorImage;

	/**
	 * 权限ID
	 */
	private String roleId;

	private BigDecimal tip;

	private String merchantNo;

	private String paymentNo;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	private BigDecimal grade;

	private Integer status;

	private Boolean delFlag;

	private String tipPaymentNo;

	private String content;
	
	private BigDecimal beanTip;

	public String getOperatorImage() {
		return operatorImage;
	}

	public void setOperatorImage(String operatorImage) {
		this.operatorImage = operatorImage;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getTipPaymentNo() {
		return tipPaymentNo;
	}

	public void setTipPaymentNo(String tipPaymentNo) {
		this.tipPaymentNo = tipPaymentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getBeanTip() {
		return beanTip;
	}

	public void setBeanTip(BigDecimal beanTip) {
		this.beanTip = beanTip;
	}
	
}