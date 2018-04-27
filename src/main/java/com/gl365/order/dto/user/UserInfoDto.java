package com.gl365.order.dto.user;

import java.io.Serializable;

public class UserInfoDto implements Serializable {

	private static final long serialVersionUID = 8319780298551486227L;

	private String userId;

	private String userName;

	private String telphone;

	private String imgUrl;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "UserInfoDto [userId=" + userId + ", userName=" + userName + ", telphone=" + telphone + ", imgUrl="
				+ imgUrl + "]";
	}

}
