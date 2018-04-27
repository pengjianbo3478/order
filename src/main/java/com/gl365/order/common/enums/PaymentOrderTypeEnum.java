package com.gl365.order.common.enums;

public enum PaymentOrderTypeEnum {

	NORMAL("1", "正常订单"), // （默认为1）
	REWARD("2", "打赏订单"), EXPERT("3", "达人订单"), ONLINE_SHOPPING("4", "网购订单"), GROUP_PAY("7", "群买单订单");

	private String value;
	private String desc;

	private PaymentOrderTypeEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
