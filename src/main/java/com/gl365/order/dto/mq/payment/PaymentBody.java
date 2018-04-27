package com.gl365.order.dto.mq.payment;

import com.gl365.order.dto.mq.payment.model.PayDetail;
import com.gl365.order.dto.mq.payment.model.PayMain;
import com.gl365.order.dto.mq.payment.model.PayPrepay;
import com.gl365.order.dto.mq.payment.model.PayReturn;
import com.gl365.order.dto.mq.payment.model.PayStream;

public class PaymentBody {
	private PayMain payMain;
	private PayReturn payReturn;
	private PayStream payStream;
	private PayDetail payDetail;
	private PayPrepay payPrepay;
	private String payModifyStatus;

	/**
	 * @return the payMain
	 */
	public PayMain getPayMain() {
		return payMain;
	}

	/**
	 * @param payMain
	 *            the payMain to set
	 */
	public void setPayMain(PayMain payMain) {
		this.payMain = payMain;
	}

	/**
	 * @return the payReturn
	 */
	public PayReturn getPayReturn() {
		return payReturn;
	}

	/**
	 * @param payReturn
	 *            the payReturn to set
	 */
	public void setPayReturn(PayReturn payReturn) {
		this.payReturn = payReturn;
	}

	/**
	 * @return the payStream
	 */
	public PayStream getPayStream() {
		return payStream;
	}

	/**
	 * @param payStream
	 *            the payStream to set
	 */
	public void setPayStream(PayStream payStream) {
		this.payStream = payStream;
	}

	/**
	 * @return the payDetail
	 */
	public PayDetail getPayDetail() {
		return payDetail;
	}

	/**
	 * @param payDetail
	 *            the payDetail to set
	 */
	public void setPayDetail(PayDetail payDetail) {
		this.payDetail = payDetail;
	}

	/**
	 * @return the payPrepay
	 */
	public PayPrepay getPayPrepay() {
		return payPrepay;
	}

	/**
	 * @param payPrepay
	 *            the payPrepay to set
	 */
	public void setPayPrepay(PayPrepay payPrepay) {
		this.payPrepay = payPrepay;
	}

	/**
	 * @return the payModifyStatus
	 */
	public String getPayModifyStatus() {
		return payModifyStatus;
	}

	/**
	 * @param payModifyStatus
	 *            the payModifyStatus to set
	 */
	public void setPayModifyStatus(String payModifyStatus) {
		this.payModifyStatus = payModifyStatus;
	}
}
