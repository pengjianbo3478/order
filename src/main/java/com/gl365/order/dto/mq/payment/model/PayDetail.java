package com.gl365.order.dto.mq.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayDetail implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String payId;
	/**
	 * <p>
	 * 40001：给乐
	 * </p>
	 */
	private String organCode;
	/**
	 * <p>
	 * 支付账户（乐豆：用户ID，其他机构：机构账户号）
	 * </p>
	 */
	private String payAccount;
	/**
	 * <p>
	 * 00：现金 01：乐豆02：机构积分03：商户积分 04：红包
	 * </p>
	 */
	private String payType;
	private BigDecimal payAmount;
	private String createBy;

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	/**
	 * <p>
	 * 40001：给乐
	 * </p>
	 * 
	 * @return
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * <p>
	 * 40001：给乐
	 * </p>
	 * 
	 * @param organCode
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode == null ? null : organCode.trim();
	}

	/**
	 * <p>
	 * 00：现金 01：乐豆02：机构积分03：商户积分 04：红包
	 * </p>
	 * 
	 * @return
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * <p>
	 * 00：现金 01：乐豆02：机构积分03：商户积分 04：红包
	 * </p>
	 * 
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	/**
	 * <p>
	 * 支付账户（乐豆：用户ID，其他机构：机构账户号）
	 * </p>
	 * 
	 * @return
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * 
	 * @param payAmount
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	/**
	 * <p>
	 * 支付账户（乐豆：用户ID，其他机构：机构账户号）
	 * </p>
	 * 
	 * @return the payAccount
	 */
	public String getPayAccount() {
		return payAccount;
	}

	/**
	 * <p>
	 * 支付账户（乐豆：用户ID，其他机构：机构账户号）
	 * </p>
	 * 
	 * @param payAccount
	 *            the payAccount to set
	 */
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

}