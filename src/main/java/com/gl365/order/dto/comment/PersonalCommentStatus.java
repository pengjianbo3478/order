package com.gl365.order.dto.comment;

import java.io.Serializable;

import com.gl365.order.common.utils.JsonUtils;

public class PersonalCommentStatus implements Serializable{

	private static final long serialVersionUID = -6677293473269847900L;

	private String paymentNo;
	
	private int status;

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
	
}
