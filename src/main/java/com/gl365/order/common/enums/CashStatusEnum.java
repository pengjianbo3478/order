package com.gl365.order.common.enums;

public enum CashStatusEnum {

	Apply(0, "申请"), // （默认为1）
	Pass(1, "通过"), 
	Reject(2, "拒绝"), 
	Succeed(3, "提现成功"),
	Fail(4, "提现失败"), ;

	private Integer value;
	private String desc;

	private CashStatusEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
