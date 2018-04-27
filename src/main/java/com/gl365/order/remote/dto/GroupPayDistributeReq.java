package com.gl365.order.remote.dto;

import io.swagger.annotations.ApiModelProperty;

public class GroupPayDistributeReq {

	@ApiModelProperty("群号")
	private String groupId ;
	
	@ApiModelProperty("当前用户的userId")
	private String userId ;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
