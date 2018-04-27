package com.gl365.order.enums;

public enum BeanRefundStatusEnum {
	INIT("INIT","发起退款"),
	SUCCESS("SUCCESS","退款成功"),
	FAIL("FAIL","退款失败"),
	;
	
	private final String status;
	private final String desc;
	
	private BeanRefundStatusEnum(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}
	
}
