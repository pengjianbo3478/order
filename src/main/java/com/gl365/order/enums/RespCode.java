package com.gl365.order.enums;


public enum RespCode {
	SUCCESS("000000", "成功"),
	
	ERROR("999999", "失败");
	
	private String code;
	
	private String text;
	
	private RespCode(String code,String text){
		this.code = code ;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
