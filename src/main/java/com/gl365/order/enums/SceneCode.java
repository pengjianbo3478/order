package com.gl365.order.enums;


public enum SceneCode {
	BARP01("0","条码支付（反扫"),
	BARQ01("5","条码支付查询"),
	;
	
	private String value;
	
	private String text;
	
	private SceneCode(String value,String text){
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
