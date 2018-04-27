package com.gl365.order.enums;

public enum OrderTypeEnum {

	NORMAL(1, "正常订单"), // （默认为1）
	REWARD(2, "打赏订单"),
	EXPERT(3, "达人订单"), 
	ONLINE_SHOPPING(4, "网购订单"),
	beanDs(5, "乐豆打赏"),
	beanPay(6, "C到C乐豆支付"),
	groupPay(7, "群支付");

	
	private int value;
	private String desc;

	private OrderTypeEnum(int value, String desc) {
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
