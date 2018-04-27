package com.gl365.order.enums;

public enum RevokeStatusEnum {

	Revoke(1, "已撤销"),;




	private int value;
	private String desc;

	private RevokeStatusEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
