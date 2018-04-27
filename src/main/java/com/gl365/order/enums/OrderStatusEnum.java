package com.gl365.order.enums;

public enum OrderStatusEnum {

	NON_PAYMENT(0, "未付款"),
	COMPLETE_PAYMENT(1, "完成付款"),
	PROCESS_PAYMENT(2, "付款中"),
	REVOKE_PAYMENT(3, "撤销"),
	REVERSAL_PAYMENT(4, "冲正"),
	ALL_REFUND(5, "网上消费退货"),
	PART_REFUND(6, "网上消费部分退货"),
	TIME_OUT_INVALID(7, "超时未支付作废状态"),
	TIME_REFUND(8, "退款进行中"),
	PAY_FAIL(9, "付款失败"),
	NON_DATA(10, "没有数据"),//退款用
	UNKNOWN_REFUND(11, "退款状态未知"),
	fail_REFUND(12, "退款失败");




	private int value;
	private String desc;

	private OrderStatusEnum(int value, String desc) {
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
