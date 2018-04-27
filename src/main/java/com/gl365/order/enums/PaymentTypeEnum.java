package com.gl365.order.enums;

public enum PaymentTypeEnum {

	/* 消费成功 */
	POS_XF("1000", "POS消费（即消费确认）"), //
	WSXF("1100", "网上消费"), //
	DSZF("1102", "打赏支付"), //
	SQQR("4100", "预授权完成确认"), //

	/* 消费撤销、冲正、退货 */
	POS_XF_CZ("1001", "POS消费冲正"), //
	WSXF_CZ("1101", "网上消费冲正"), //
	SQQR_CZ("4101", "预授权完成确认冲正"), //

	POS_CX("2000", "POS撤销"), //
	WSXF_CX("2100", "网上消费撤销"), //

	POS_TH("3000", "POS退货"), //
	POS_BF_TH("3100", "POS部分退货"), //
	WSXF_TH("3200", "网上消费退货"), //
	WSXF_BF_TH("3300", "网上消费部分退货"), //

	/* 撤销冲正 */
	POS_CX_CZ("2001", "POS撤销冲正"), //
	WSXF_CX_CZ("2101", "网上消费撤销冲正"), //

	POS_TH_CZ("3001", "POS退货冲正"), //
	WSXF_TH_CZ("3201", "网上消费退货冲正"),//
	/* 其他 */
	;
	private String value;
	private String desc;

	private PaymentTypeEnum(String value, String desc) {
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

	private static boolean typeContain(String type, PaymentTypeEnum... enums) {
		for (PaymentTypeEnum eType : enums) {
			if (eType.getValue().equals(type))
				return true;
		}
		return false;
	}

	/**
	 * 包含POS消费的交易类型
	 * 
	 * @param type
	 * @return
	 */
	public static boolean containPOS(String type) {
		return typeContain(type, POS_BF_TH, POS_CX, POS_CX_CZ, POS_TH, POS_TH_CZ, POS_XF, POS_XF_CZ);
	}

	/**
	 * 包含冲正订单的交易类型
	 * 
	 * @param type
	 * @return
	 */
	public static boolean containCZ(String type) {
		return typeContain(type, POS_XF_CZ, WSXF_CZ, SQQR_CZ, POS_CX_CZ, WSXF_CX_CZ, POS_TH_CZ, WSXF_TH_CZ);
	}

	/**
	 * 包含退货订单的交易类型
	 * 
	 * @param type
	 * @return
	 */
	public static boolean containTH(String type) {
		return typeContain(type, POS_TH, POS_BF_TH, WSXF_TH, WSXF_BF_TH);
	}

	/**
	 * 包含撤销订单的交易类型
	 * 
	 * @param type
	 * @return
	 */
	public static boolean containCX(String type) {
		return typeContain(type, POS_CX, WSXF_CZ);
	}
}
