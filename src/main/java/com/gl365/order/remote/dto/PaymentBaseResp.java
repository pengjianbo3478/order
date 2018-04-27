package com.gl365.order.remote.dto;

import java.io.Serializable;

import com.gl365.order.util.JsonUtil;

public class PaymentBaseResp implements Serializable{

	private static final long serialVersionUID = 2504977902842817260L;

	private String resultCode;
	
	private String resultDesc;
	
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

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
}
