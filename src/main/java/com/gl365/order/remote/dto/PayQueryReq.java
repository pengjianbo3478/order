package com.gl365.order.remote.dto;

import java.io.Serializable;

public class PayQueryReq implements Serializable{

	private static final long serialVersionUID = 7399790943136100539L;

	private String groupOrderId;
	
	private String userId;

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
