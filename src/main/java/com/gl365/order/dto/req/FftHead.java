package com.gl365.order.dto.req;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.dto.BaseEntity;

/**
 * 付费通扫码支付接口：请求报文公共参数信息
 */
public class FftHead extends BaseEntity {
		
	private static final long serialVersionUID = 1L;

	/**
	 * 版本号
	 * 目前版本“1.0.0”
	 */
	private String version = "1.0.0";
		
	/**
	 * 请求日期时间
	 * 格式为yyyyMMddHHmmss
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private String reqDate;	
	
	/**
	 * 备用域 1
	 */
	private String extend1 = "";
	/**
	 * 备用域 2
	 */
	private String extend2 = "";
	
	/**
	 * 备用域 3
	 */
	private String extend3 = "";

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}	
		
	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	
}
