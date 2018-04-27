package com.gl365.order.dto.mq.payment.model;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
public class PayMain implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String payId;
	private String requestId;
	/**
	 * 查询流水号
	 */
	private String prePayId;
	private String organCode;
	private String organMerchantNo;
	/**
	 * 支付公司订单号
	 */
	private String organOrderNo;
	private String merchantNo;
	private String merchantName;
	private String terminal = StringUtils.EMPTY;
	private String operator;
	private String merchantAgentNo;
	private String userAgentType;
	private String userAgentNo;
	/**
	 * 订单类型
	        1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1）
	        2：打赏订单
	        3：达人订单
	        4：网购订单
	 */
	private String orderType;
	/**
	 * 发展会员商家店长
	 */
	private String userDevManager;
	/**
	 * 发展会员商家员工
	 */
	private String userDevStaff;
	/**
	 * 清算支付公司
	 */
	private String settleOrganNo;
	/**
	 * 发展商户机构上级机构
	 */
	private String parentAgentNo;
	/**
	 * 推广业务员
	 */
	private String inviteAgentNo;
	/**
	 * 会员手机
	 */
	private String userMobile;
	/**
	 * 支付卡号
	 */
	private String cardNo;
	private Short province;
	private Short city;
	private Short district;
	private String transType;
	private String scene;
	private String merchantOrderTitle;
	private String merchentOrderDesc;
	private String merchantOrderNo;
	/**
	 * 被打赏员工	String	32	否
	 */
	private String rewardUserId; // 被打赏员工 String 32 否
	/**
	 * 被打赏原支付单号	String	32	否
	 */
	private String rewardPayId;
	private String userId;
	private String userName;
	private String cardIndex;
	private BigDecimal totalAmount;
	private BigDecimal noBenefitAmount;
	/**
	 * 支付手续费类型（借贷）/卡类型
	 */
	private String payFeeType;
	/**
	 * 支付手续费上限值
	 */
	private BigDecimal maxPayFee;
	private BigDecimal payFee;
	private BigDecimal cashAmount;
	private BigDecimal beanAmount;
	private BigDecimal coinAmount;
	private BigDecimal payFeeRate;
	/**
	 * 返佣率
	 */
	private BigDecimal commRate;
	private BigDecimal commAmount;
	private BigDecimal marcketFee;
	/**
	 * 返利率
	 */
	private BigDecimal giftRate;
	private BigDecimal giftAmount;
	private BigDecimal giftPoint;
	/**
	 * 商户清算金额
	 */
	private BigDecimal merchantSettleAmount;
	private String payStatus;
	private String payDesc;
	private String isNotify;
	private String createBy;
	private String modifyBy;
	
	
	/**
	 * 群组单号或达人活动单号
	 */
	private String groupOrderId;
	/**
	 * 分单标志：\n0-主单\n1-子单（分单）
	 */
	private String splitFlag;
	/**
	 * 群组发起人员应支付金额
	 */
	private BigDecimal groupMainuserPay;
	/**
	 * 群平台支付总额
	 */
	private BigDecimal groupPtPay;
	/**
	 * 群组发起人支付乐豆
	 */
	private BigDecimal groupMainuserPayBean;
	/**
	 * 群成员本单返乐豆
	 */
	private BigDecimal groupGiftAmount;
	/**
	 * 群组消费商家
	 */
	private String groupMerchantNo;
	/**
	 * 通道订单号
	 */
	private String transactionId;

	

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId == null ? null : requestId.trim();
	}


	public String getPrePayId() {
		return prePayId;
	}

	public void setPrePayId(String prePayId) {
		this.prePayId = prePayId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode == null ? null : organCode.trim();
	}

	public String getOrganMerchantNo() {
		return organMerchantNo;
	}

	public void setOrganMerchantNo(String organMerchantNo) {
		this.organMerchantNo = organMerchantNo == null ? null : organMerchantNo.trim();
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo == null ? null : merchantNo.trim();
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal == null ? null : terminal.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getMerchantAgentNo() {
		return merchantAgentNo;
	}

	public void setMerchantAgentNo(String merchantAgentNo) {
		this.merchantAgentNo = merchantAgentNo == null ? null : merchantAgentNo.trim();
	}

	public String getUserAgentType() {
		return userAgentType;
	}

	public void setUserAgentType(String userAgentType) {
		this.userAgentType = userAgentType == null ? null : userAgentType.trim();
	}

	public String getUserAgentNo() {
		return userAgentNo;
	}

	public void setUserAgentNo(String userAgentNo) {
		this.userAgentNo = userAgentNo == null ? null : userAgentNo.trim();
	}

	public Short getProvince() {
		return province;
	}

	public void setProvince(Short province) {
		this.province = province;
	}

	public Short getCity() {
		return city;
	}

	public void setCity(Short city) {
		this.city = city;
	}

	public Short getDistrict() {
		return district;
	}

	public void setDistrict(Short district) {
		this.district = district;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType == null ? null : transType.trim();
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene == null ? null : scene.trim();
	}

	public String getMerchantOrderTitle() {
		return merchantOrderTitle;
	}

	public void setMerchantOrderTitle(String merchantOrderTitle) {
		this.merchantOrderTitle = merchantOrderTitle == null ? null : merchantOrderTitle.trim();
	}

	public String getMerchentOrderDesc() {
		return merchentOrderDesc;
	}

	public void setMerchentOrderDesc(String merchentOrderDesc) {
		this.merchentOrderDesc = merchentOrderDesc == null ? null : merchentOrderDesc.trim();
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
	}

	public String getRewardUserId() {
		return rewardUserId;
	}

	public void setRewardUserId(String rewardUserId) {
		this.rewardUserId = rewardUserId;
	}

	public String getRewardPayId() {
		return rewardPayId;
	}

	public void setRewardPayId(String rewardPayId) {
		this.rewardPayId = rewardPayId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex == null ? null : cardIndex.trim();
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getNoBenefitAmount() {
		return noBenefitAmount;
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	public String getPayFeeType() {
		return payFeeType;
	}

	public void setPayFeeType(String payFeeType) {
		this.payFeeType = payFeeType;
	}

	public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getBeanAmount() {
		return beanAmount;
	}

	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}

	public BigDecimal getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	public BigDecimal getPayFeeRate() {
		return payFeeRate;
	}

	public void setPayFeeRate(BigDecimal payFeeRate) {
		this.payFeeRate = payFeeRate;
	}

	public BigDecimal getCommRate() {
		return commRate;
	}

	public void setCommRate(BigDecimal commRate) {
		this.commRate = commRate;
	}

	public BigDecimal getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}

	public BigDecimal getMarcketFee() {
		return marcketFee;
	}

	public void setMarcketFee(BigDecimal marcketFee) {
		this.marcketFee = marcketFee;
	}

	public BigDecimal getGiftRate() {
		return giftRate;
	}

	public void setGiftRate(BigDecimal giftRate) {
		this.giftRate = giftRate;
	}

	/**
	 * 返利金额
	 * @return
	 */
	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	/**
	 * 返利金额
	 * @param giftAmount
	 */
	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	public BigDecimal getGiftPoint() {
		return giftPoint;
	}

	public void setGiftPoint(BigDecimal giftPoint) {
		this.giftPoint = giftPoint;
	}

	public BigDecimal getMerchantSettleAmount() {
		return merchantSettleAmount;
	}

	public void setMerchantSettleAmount(BigDecimal merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus == null ? null : payStatus.trim();
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc == null ? null : payDesc.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getModifyBy() {
		return modifyBy;
	}


	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}


	public String getUserDevManager() {
		return userDevManager;
	}

	public void setUserDevManager(String userDevManager) {
		this.userDevManager = userDevManager;
	}

	public String getUserDevStaff() {
		return userDevStaff;
	}

	public void setUserDevStaff(String userDevStaff) {
		this.userDevStaff = userDevStaff;
	}

	public String getSettleOrganNo() {
		return settleOrganNo;
	}

	public void setSettleOrganNo(String settleOrganNo) {
		this.settleOrganNo = settleOrganNo;
	}

	public String getParentAgentNo() {
		return parentAgentNo;
	}

	public void setParentAgentNo(String parentAgentNo) {
		this.parentAgentNo = parentAgentNo;
	}

	public String getInviteAgentNo() {
		return inviteAgentNo;
	}

	public void setInviteAgentNo(String inviteAgentNo) {
		this.inviteAgentNo = inviteAgentNo;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getMaxPayFee() {
		return maxPayFee;
	}

	public void setMaxPayFee(BigDecimal maxPayFee) {
		this.maxPayFee = maxPayFee;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrganOrderNo() {
		return organOrderNo;
	}

	public void setOrganOrderNo(String organOrderNo) {
		this.organOrderNo = organOrderNo;
	}

	public String getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}

	public String getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(String groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	public String getSplitFlag() {
		return splitFlag;
	}

	public void setSplitFlag(String splitFlag) {
		this.splitFlag = splitFlag;
	}

	public BigDecimal getGroupMainuserPay() {
		return groupMainuserPay;
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}

	public BigDecimal getGroupPtPay() {
		return groupPtPay;
	}

	public void setGroupPtPay(BigDecimal groupPtPay) {
		this.groupPtPay = groupPtPay;
	}

	public BigDecimal getGroupMainuserPayBean() {
		return groupMainuserPayBean;
	}

	public void setGroupMainuserPayBean(BigDecimal groupMainuserPayBean) {
		this.groupMainuserPayBean = groupMainuserPayBean;
	}

	public BigDecimal getGroupGiftAmount() {
		return groupGiftAmount;
	}

	public void setGroupGiftAmount(BigDecimal groupGiftAmount) {
		this.groupGiftAmount = groupGiftAmount;
	}

	public String getGroupMerchantNo() {
		return groupMerchantNo;
	}

	public void setGroupMerchantNo(String groupMerchantNo) {
		this.groupMerchantNo = groupMerchantNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}