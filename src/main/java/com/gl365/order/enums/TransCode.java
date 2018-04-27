package com.gl365.order.enums;


public enum TransCode {
	MER01("100001","商户进件"),
	MER02("200001","商户查询"),
	MER03("300001","商户修改"),
	MER04("400001","新增终端");

	
	private String value;
	
	private String text;
	
	private TransCode(String value,String text){
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
