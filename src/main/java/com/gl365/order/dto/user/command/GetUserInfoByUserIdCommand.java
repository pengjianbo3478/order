package com.gl365.order.dto.user.command;

public class GetUserInfoByUserIdCommand {

	private String userId;

	public GetUserInfoByUserIdCommand() {
		super();
	}

	public GetUserInfoByUserIdCommand(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
