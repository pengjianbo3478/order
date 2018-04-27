package com.gl365.order.remote.dto;

import java.io.Serializable;

import com.gl365.order.util.JsonUtil;

public class CancelResultReq implements Serializable{

	private static final long serialVersionUID = -1315277865896903839L;

	private String organCode; //微信：10003
	
	private String merchantOrderNo; //退款订单号

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	
	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
}
