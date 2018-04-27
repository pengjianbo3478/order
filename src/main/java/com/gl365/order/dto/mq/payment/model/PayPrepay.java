package com.gl365.order.dto.mq.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;


public class PayPrepay implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	/**
	 * 给乐流水号
	 */
	private String payId;
	/**
	 * 请求流水号
	 */
	private String requestId;
	/**
	 * 支付机构代码
	 */
	private String organCode;
	/**
	 * 支付机构商户号
	 */
	private String organMerchantNo;
	/**
	 * 给乐商户号
	 */
	private String merchantNo;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 终端号
	 */
	private String terminal;
	/**
	 * 操作员
	 */
	private String operator;
	/**
	 * 清算支付公司
	 */
	private String settleOrganNo;
	/**
	 * 发展商户机构
	 */
	private String merchantAgentNo;
	/**
	 * 发展商户机构上级机构
	 */
	private String parentAgentNo;
	/**
	 * 推广业务员
	 */
	private String inviteAgentNo;
	/**
	 * 发展会员机构类型
	 */
	private String userAgentType;
	/**
	 * 发展会员机构
	 */
	private String userAgentNo;
	/**
	 * 发展会员商家店长
	 */
	private String userDevManager;
	/**
	 * 发展会员商家员工
	 */
	private String userDevStaff;
	/**
	 * 商家所在省
	 */
	private Short province;
	/**
	 * 商家所在市
	 */
	private Short city;
	/**
	 * 商家所在区
	 */
	private Short district;
	/**
	 * 交易类型
	 */
	private String transType;
	/**
	 * 支付场景
	 */
	private String scene;
	/**
	 * 订单类型 1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1） 2：打赏订单 3：达人订单 4：网购订单
	 */
	private String orderType;
	/**
	 * 订单标题
	 */
	private String merchantOrderTitle;
	/**
	 * 订单描述
	 */
	private String merchentOrderDesc;
	/**
	 * 商户订单号
	 */
	private String merchantOrderNo;
	/**
	 * 会员号
	 */
	private String userId;
	/**
	 * 会员姓名
	 */
	private String userName;
	/**
	 * 会员手机号
	 */
	private String userMobile;
	/**
	 * 绑卡索引号
	 */
	private String cardIndex;
	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 交易总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 不可返利金额
	 */
	private BigDecimal noBenefitAmount;
	/**
	 * 支付手续费
	 */
	private BigDecimal payFee;
	/**
	 * 现金支付金额
	 */
	private BigDecimal cashAmount;
	/**
	 * 乐豆金额
	 */
	private BigDecimal beanAmount;
	/**
	 * 乐币金额
	 */
	private BigDecimal coinAmount;
	/**
	 * 支付手续费率
	 */
	private BigDecimal payFeeRate;
	/**
	 * 支付手续费费率类型（借贷）
	 */
	private String payFeeType;
	/**
	 * 佣金率
	 */
	private BigDecimal commRate;
	/**
	 * 支付手续费费率
	 */
	private BigDecimal maxPayFee;
	/**
	 * 返佣金额
	 */
	private BigDecimal commAmount;
	/**
	 * 营销费
	 */
	private BigDecimal marcketFee;
	/**
	 * 返利率
	 */
	private BigDecimal giftRate;
	/**
	 * 返利金额
	 */
	private BigDecimal giftAmount;
	/**
	 * 赠送积分
	 */
	private BigDecimal giftPoint;
	/**
	 * 商户实得金额
	 */
	private BigDecimal merchantSettleAmount;
	/**
	 * 交易状态
	 */
	private String payStatus;
	/**
	 * 交易描述
	 */
	private String payDesc;
	private String createBy;
	private String modifyBy;

	/**
	 * 给乐流水号
	 * 
	 * @return the payId
	 */
	public String getPayId() {
		return payId;
	}

	/**
	 * 给乐流水号
	 * 
	 * @param payId
	 *            the payId to set
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}

	/**
	 * 请求流水号
	 * 
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * 请求流水号
	 * 
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * 支付机构代码
	 * 
	 * @return the organCode
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * 支付机构代码
	 * 
	 * @param organCode
	 *            the organCode to set
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	/**
	 * 支付机构商户号
	 * 
	 * @return the organMerchantNo
	 */
	public String getOrganMerchantNo() {
		return organMerchantNo;
	}

	/**
	 * 支付机构商户号
	 * 
	 * @param organMerchantNo
	 *            the organMerchantNo to set
	 */
	public void setOrganMerchantNo(String organMerchantNo) {
		this.organMerchantNo = organMerchantNo;
	}

	/**
	 * 给乐商户号
	 * 
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * 给乐商户号
	 * 
	 * @param merchantNo
	 *            the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * 给乐商户名
	 * 
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 给乐商户名
	 * 
	 * @param merchantName
	 *            the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * 终端号
	 * 
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * 终端号
	 * 
	 * @param terminal
	 *            the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * 操作员
	 * 
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 操作员
	 * 
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 发展商户机构
	 * 
	 * @return the merchantAgentNo
	 */
	public String getMerchantAgentNo() {
		return merchantAgentNo;
	}

	/**
	 * 发展商户机构
	 * 
	 * @param merchantAgentNo
	 *            the merchantAgentNo to set
	 */
	public void setMerchantAgentNo(String merchantAgentNo) {
		this.merchantAgentNo = merchantAgentNo;
	}

	/**
	 * 发展会员机构类型
	 * 
	 * @return the userAgentType
	 */
	public String getUserAgentType() {
		return userAgentType;
	}

	/**
	 * 发展会员机构类型
	 * 
	 * @param userAgentType
	 *            the userAgentType to set
	 */
	public void setUserAgentType(String userAgentType) {
		this.userAgentType = userAgentType;
	}

	/**
	 * <p>
	 * 1-代理商 2-商家
	 * </p>
	 * 发展会员机构
	 * 
	 * @return the userAgentNo
	 */
	public String getUserAgentNo() {
		return userAgentNo;
	}

	/**
	 * <p>
	 * 1-代理商 2-商家
	 * </p>
	 * 发展会员机构
	 * 
	 * @param userAgentNo
	 *            the userAgentNo to set
	 */
	public void setUserAgentNo(String userAgentNo) {
		this.userAgentNo = userAgentNo;
	}

	/**
	 * 商家所在省
	 * 
	 * @return the province
	 */
	public Short getProvince() {
		return province;
	}

	/**
	 * 商家所在省
	 * 
	 * @param province
	 *            the province to set
	 */
	public void setProvince(Short province) {
		this.province = province;
	}

	/**
	 * 商家所在市
	 * 
	 * @return the city
	 */
	public Short getCity() {
		return city;
	}

	/**
	 * 商家所在市
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(Short city) {
		this.city = city;
	}

	/**
	 * 商家所在区
	 * 
	 * @return the district
	 */
	public Short getDistrict() {
		return district;
	}

	/**
	 * 商家所在区
	 * 
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(Short district) {
		this.district = district;
	}

	/**
	 * 交易类型
	 * 
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * 交易类型
	 * 
	 * @param transType
	 *            the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * 支付场景
	 * 
	 * @return the scene
	 */
	public String getScene() {
		return scene;
	}

	/**
	 * 支付场景
	 * 
	 * @param scene
	 *            the scene to set
	 */
	public void setScene(String scene) {
		this.scene = scene;
	}

	/**
	 * 订单标题
	 * 
	 * @return the merchantOrderTitle
	 */
	public String getMerchantOrderTitle() {
		return merchantOrderTitle;
	}

	/**
	 * 订单标题
	 * 
	 * @param merchantOrderTitle
	 *            the merchantOrderTitle to set
	 */
	public void setMerchantOrderTitle(String merchantOrderTitle) {
		this.merchantOrderTitle = merchantOrderTitle;
	}

	/**
	 * 订单描述
	 * 
	 * @return the merchentOrderDesc
	 */
	public String getMerchentOrderDesc() {
		return merchentOrderDesc;
	}

	/**
	 * 订单描述
	 * 
	 * @param merchentOrderDesc
	 *            the merchentOrderDesc to set
	 */
	public void setMerchentOrderDesc(String merchentOrderDesc) {
		this.merchentOrderDesc = merchentOrderDesc;
	}

	/**
	 * 订单号
	 * 
	 * @return the merchantOrderNo
	 */
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	/**
	 * 订单号
	 * 
	 * @param merchantOrderNo
	 *            the merchantOrderNo to set
	 */
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 绑卡索引号
	 * 
	 * @return the cardIndex
	 */
	public String getCardIndex() {
		return cardIndex;
	}

	/**
	 * 绑卡索引号
	 * 
	 * @param cardIndex
	 *            the cardIndex to set
	 */
	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex;
	}

	/**
	 * 交易总金额
	 * 
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 交易总金额
	 * 
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 不可返利金额
	 * 
	 * @return the noBenefitAmount
	 */
	public BigDecimal getNoBenefitAmount() {
		return noBenefitAmount;
	}

	/**
	 * 不可返利金额
	 * 
	 * @param noBenefitAmount
	 *            the noBenefitAmount to set
	 */
	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	/**
	 * 支付手续费
	 * 
	 * @return the payFee
	 */
	public BigDecimal getPayFee() {
		return payFee;
	}

	/**
	 * 支付手续费
	 * 
	 * @param payFee
	 *            the payFee to set
	 */
	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	/**
	 * 现金支付金额
	 * 
	 * @return the cashAmount
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	/**
	 * 现金支付金额
	 * 
	 * @param cashAmount
	 *            the cashAmount to set
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	/**
	 * 乐豆金额
	 * 
	 * @return the beanAmount
	 */
	public BigDecimal getBeanAmount() {
		return beanAmount;
	}

	/**
	 * 乐豆金额
	 * 
	 * @param beanAmount
	 *            the beanAmount to set
	 */
	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}

	/**
	 * 乐币金额
	 * 
	 * @return the coinAmount
	 */
	public BigDecimal getCoinAmount() {
		return coinAmount;
	}

	/**
	 * 乐币金额
	 * 
	 * @param coinAmount
	 *            the coinAmount to set
	 */
	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	/**
	 * 支付手续费率
	 * 
	 * @return the payFeeRate
	 */
	public BigDecimal getPayFeeRate() {
		return payFeeRate;
	}

	/**
	 * 支付手续费率
	 * 
	 * @param payFeeRate
	 *            the payFeeRate to set
	 */
	public void setPayFeeRate(BigDecimal payFeeRate) {
		this.payFeeRate = payFeeRate;
	}

	/**
	 * 支付手续费费率类型（借贷）
	 * 
	 * @return the payFeeType
	 */
	public String getPayFeeType() {
		return payFeeType;
	}

	/**
	 * 支付手续费费率类型（借贷）
	 * 
	 * @param payFeeType
	 *            the payFeeType to set
	 */
	public void setPayFeeType(String payFeeType) {
		this.payFeeType = payFeeType;
	}

	/**
	 * 佣金率
	 * 
	 * @return the commRate
	 */
	public BigDecimal getCommRate() {
		return commRate;
	}

	/**
	 * 佣金率
	 * 
	 * @param commRate
	 *            the commRate to set
	 */
	public void setCommRate(BigDecimal commRate) {
		this.commRate = commRate;
	}

	/**
	 * 支付手续费上限值
	 * 
	 * @return the maxPayFee
	 */
	public BigDecimal getMaxPayFee() {
		return maxPayFee;
	}

	/**
	 * 支付手续费上限值
	 * 
	 * @param maxPayFee
	 *            the maxPayFee to set
	 */
	public void setMaxPayFee(BigDecimal maxPayFee) {
		this.maxPayFee = maxPayFee;
	}

	/**
	 * 返佣金额
	 * 
	 * @return the commAmount
	 */
	public BigDecimal getCommAmount() {
		return commAmount;
	}

	/**
	 * 返佣金额
	 * 
	 * @param commAmount
	 *            the commAmount to set
	 */
	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}

	/**
	 * 营销费
	 * 
	 * @return the marcketFee
	 */
	public BigDecimal getMarcketFee() {
		return marcketFee;
	}

	/**
	 * 营销费
	 * 
	 * @param marcketFee
	 *            the marcketFee to set
	 */
	public void setMarcketFee(BigDecimal marcketFee) {
		this.marcketFee = marcketFee;
	}

	/**
	 * 返利率
	 * 
	 * @return the giftRate
	 */
	public BigDecimal getGiftRate() {
		return giftRate;
	}

	/**
	 * 返利率
	 * 
	 * @param giftRate
	 *            the giftRate to set
	 */
	public void setGiftRate(BigDecimal giftRate) {
		this.giftRate = giftRate;
	}

	/**
	 * 返利金额
	 * 
	 * @return the giftAmount
	 */
	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	/**
	 * 返利金额
	 * 
	 * @param giftAmount
	 *            the giftAmount to set
	 */
	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	/**
	 * 赠送积分
	 * 
	 * @return the giftPoint
	 */
	public BigDecimal getGiftPoint() {
		return giftPoint;
	}

	/**
	 * 赠送积分
	 * 
	 * @param giftPoint
	 *            the giftPoint to set
	 */
	public void setGiftPoint(BigDecimal giftPoint) {
		this.giftPoint = giftPoint;
	}

	/**
	 * 商户实得金额
	 * 
	 * @return the merchantSettleAmount
	 */
	public BigDecimal getMerchantSettleAmount() {
		return merchantSettleAmount;
	}

	/**
	 * 商户实得金额
	 * 
	 * @param merchantSettleAmount
	 *            the merchantSettleAmount to set
	 */
	public void setMerchantSettleAmount(BigDecimal merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
	}

	/**
	 * 交易状态
	 * 
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 交易状态
	 * 
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the payDesc
	 */
	public String getPayDesc() {
		return payDesc;
	}

	/**
	 * @param payDesc
	 *            the payDesc to set
	 */
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy
	 *            the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}

	@Override
	public PayPrepay clone() {
		try {
			return (PayPrepay) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发展会员商家店长
	 * 
	 * @return the userDevManager
	 */
	public String getUserDevManager() {
		return userDevManager;
	}

	/**
	 * 发展会员商家店长
	 * 
	 * @param userDevManager
	 *            the userDevManager to set
	 */
	public void setUserDevManager(String userDevManager) {
		this.userDevManager = userDevManager;
	}

	/**
	 * 发展会员商家员工
	 * 
	 * @return the userDevStaff
	 */
	public String getUserDevStaff() {
		return userDevStaff;
	}

	/**
	 * 发展会员商家员工
	 * 
	 * @param userDevStaff
	 *            the userDevStaff to set
	 */
	public void setUserDevStaff(String userDevStaff) {
		this.userDevStaff = userDevStaff;
	}

	/**
	 * 清算支付公司
	 * 
	 * @return the settleOrganNo
	 */
	public String getSettleOrganNo() {
		return settleOrganNo;
	}

	/**
	 * 清算支付公司
	 * 
	 * @param settleOrganNo
	 *            the settleOrganNo to set
	 */
	public void setSettleOrganNo(String settleOrganNo) {
		this.settleOrganNo = settleOrganNo;
	}

	/**
	 * 发展商户机构上级机构
	 * 
	 * @return the parentAgentNo
	 */
	public String getParentAgentNo() {
		return parentAgentNo;
	}

	/**
	 * 发展商户机构上级机构
	 * 
	 * @param parentAgentNo
	 *            the parentAgentNo to set
	 */
	public void setParentAgentNo(String parentAgentNo) {
		this.parentAgentNo = parentAgentNo;
	}

	/**
	 * 推广业务员
	 * 
	 * @return the inviteAgentNo
	 */
	public String getInviteAgentNo() {
		return inviteAgentNo;
	}

	/**
	 * 推广业务员
	 * 
	 * @param inviteAgentNo
	 *            the inviteAgentNo to set
	 */
	public void setInviteAgentNo(String inviteAgentNo) {
		this.inviteAgentNo = inviteAgentNo;
	}

	/**
	 * 会员手机号
	 * 
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}

	/**
	 * 会员手机号
	 * 
	 * @param userMobile
	 *            the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * 卡号
	 * 
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 卡号
	 * 
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 订单类型 1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1） 2：打赏订单 3：达人订单 4：网购订单
	 * 
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 订单类型 1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1） 2：打赏订单 3：达人订单 4：网购订单
	 * 
	 * @param orderType
	 *            the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}