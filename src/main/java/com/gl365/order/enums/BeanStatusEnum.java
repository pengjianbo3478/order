package com.gl365.order.enums;

public enum BeanStatusEnum {

	bean_init(0, "抵扣乐豆初始"),
	bean_success(1, "抵扣乐豆成功"),
	bean_fail(2, "抵扣乐豆失败");




	private int value;
	private String desc;

	private BeanStatusEnum(int value, String desc) {
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
