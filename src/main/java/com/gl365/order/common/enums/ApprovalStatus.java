package com.gl365.order.common.enums;

public enum ApprovalStatus {
	pass("通过", "1"), reject("拒绝", "2");

	private String desc;
	private String value;

	private ApprovalStatus(String desc, String value) {
		this.desc=desc;
		this.value=value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
