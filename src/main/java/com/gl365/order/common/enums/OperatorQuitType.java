package com.gl365.order.common.enums;

public enum OperatorQuitType {
	LEAVE("离职", 1), ONJOB("在职", 0);

	private String desc;
	private Integer value;

	private OperatorQuitType(String desc, Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
