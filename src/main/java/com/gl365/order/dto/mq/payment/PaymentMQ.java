package com.gl365.order.dto.mq.payment;

public class PaymentMQ {
	private String tranType;
	private String msgCategory;
	private PaymentBody paymentBody;

	public PaymentBody getPaymentBody() {
		return paymentBody;
	}

	public void setPaymentBody(PaymentBody paymentBody) {
		this.paymentBody = paymentBody;
	}

	/**
	 * @return the tranType
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * @param tranType
	 *            the tranType to set
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	/**
	 * @return the msgCategory
	 */
	public String getMsgCategory() {
		return msgCategory;
	}

	/**
	 * @param msgCategory
	 *            the msgCategory to set
	 */
	public void setMsgCategory(String msgCategory) {
		this.msgCategory = msgCategory;
	}
}
