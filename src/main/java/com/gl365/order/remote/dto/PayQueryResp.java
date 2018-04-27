package com.gl365.order.remote.dto;

import java.io.Serializable;

public class PayQueryResp implements Serializable{

	private static final long serialVersionUID = -7854606339218230771L;

	private String resultCode;
	
	 private String resultDesc;
	 
	 private String payId;
	 
	 private String groupOrderId;
	 
	 private String userId;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(String groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
