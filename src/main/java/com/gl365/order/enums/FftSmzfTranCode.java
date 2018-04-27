package com.gl365.order.enums;

public enum FftSmzfTranCode {
	
	BARCODE_PAY_B("BARP01","条码支付（反扫）"),
	
	BARCODE_PAY_QUERY("BARQ01","条码支付（反扫）查询订单状态"),
	
	BARCODE_PAY_CANCEL("BARC01","条码支付（反扫）撤单"),
	
	
	MERCHANT_AUTHORIZATION("MER01","商户进件"),

	MERCHANT_QUERY("MER02","商户查询"),
	
	MERCHANT_UPDATE("MER03","商户修改"),
	
	MERCHANT_ADD_TERMINAL("MER04","商户新增终端"),
	;
	
	private final String code;
	private final String desc;

	private FftSmzfTranCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}
}
