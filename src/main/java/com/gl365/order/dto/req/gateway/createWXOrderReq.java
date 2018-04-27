package com.gl365.order.dto.req.gateway;

import java.math.BigDecimal;

/**
 * 融脉订单创建
 * @author DELL
 *
 */
public class createWXOrderReq {
	
	/**
	 * gl商户号
	 */
	private String merchantNo;
	/**
	 * gl商户号
	 */
	private String organMerchantNo;
	/**
	 * 付款人id
	 */
	private String userId;
	/**
	 * 订单总金额
	 */
	private BigDecimal totalAmount;
	
	/**(选填)
	 * 支付场景
	 * 	00：直接支付（快捷支付）
		01：B扫C支付
		02：C扫B支付
		03：POS
		04：微信H5支付
	 */
	private String scene;
	
	/**(选填)
	 * 订单标题
	 */
	private String merchantOrderTitle;
	
	/**(选填)
	 * 订单描述
	 */
	private String merchantOrderDesc;
	
	/**(默认0)
	 * 不可返利金额
	 */
	private BigDecimal noBenefitAmount;
	/**
	 * 商户订单号
	 */
	private String merchantOrderNo;
	
	/**
	 * 订单类型
		1：正常订单
		2：打赏订单（现金）
		3：达人订单
		4：网购订单
		5：乐豆打赏
		6：C到C乐豆支付
		7：群支付
	 */
	private String orderType;
	/**
	 * 群组id(达人活动ID)
	 */
	private String groupOrderId;
	/**
	 * 群组(达人活动ID)发起人应支付总额
	 */
	private BigDecimal groupMainuserPay;
	
	/**
	 * 0主单1子单
	 */
	private String splitFlag;
	
	/**
	 * 群支付给乐商家
	 */
	private String groupMerchantNo;
	
	/**
	 * 支付渠道 
	 */
	private String channel;
	
	/**
	 * 附加数据 (选填)
	 */
	private String description;
	
	/**
	 * 商品的标题(选填)
	 */
	private String subject;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrganMerchantNo() {
		return organMerchantNo;
	}

	public void setOrganMerchantNo(String organMerchantNo) {
		this.organMerchantNo = organMerchantNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMerchantOrderTitle() {
		return merchantOrderTitle;
	}

	public void setMerchantOrderTitle(String merchantOrderTitle) {
		this.merchantOrderTitle = merchantOrderTitle;
	}

	public String getMerchantOrderDesc() {
		return merchantOrderDesc;
	}

	public void setMerchantOrderDesc(String merchantOrderDesc) {
		this.merchantOrderDesc = merchantOrderDesc;
	}

	public BigDecimal getNoBenefitAmount() {
		return noBenefitAmount;
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(String groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	public BigDecimal getGroupMainuserPay() {
		return groupMainuserPay;
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}

	public String getSplitFlag() {
		return splitFlag;
	}

	public void setSplitFlag(String splitFlag) {
		this.splitFlag = splitFlag;
	}

	public String getGroupMerchantNo() {
		return groupMerchantNo;
	}

	public void setGroupMerchantNo(String groupMerchantNo) {
		this.groupMerchantNo = groupMerchantNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	
}
