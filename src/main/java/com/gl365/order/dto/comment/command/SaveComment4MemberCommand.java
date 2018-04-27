package com.gl365.order.dto.comment.command;

import java.math.BigDecimal;

/**
 * < 商家评论DTO >
 * 
 * @author hui.li 2017年4月20日 - 下午4:47:32
 * @Since 1.0
 */
public class SaveComment4MemberCommand {

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
	 * 交易金额
	 */
	private BigDecimal totalAmount;

	public SaveComment4MemberCommand(String userId, String merchantNo, String paymentNo, BigDecimal totalAmount) {
		super();
		this.userId = userId;
		this.merchantNo = merchantNo;
		this.paymentNo = paymentNo;
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public SaveComment4MemberCommand() {
		super();
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

}