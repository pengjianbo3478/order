package com.gl365.order.enums;

public enum PaymentConfigEnum {

	FAST_PAYMENT(0, "快捷"), 
	B_SCAN_C(1, "b扫c"),
	C_SCAN_B(2, "c扫b"), 
	POS(4, "pos"),
	COLLECTIVE_MAIN(5, "群买单主单"),
	COLLECTIVE_MINOR(6, "群买单子单"),
	refund(8, "退款单"),
	tip_bean(9, "打赏乐豆"),
	tip_cash(10, "打赏现金");
	
	
	

	private int value;
	private String desc;

	private PaymentConfigEnum(int value, String desc) {
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
