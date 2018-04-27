package com.gl365.order.dto.rsp;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.dto.BaseEntity;
import com.gl365.order.enums.RespCode;

/**
 * 付费通扫码支付接口：响应报文公共参数
 */
public class FftResult extends BaseEntity {
		
	private static final long serialVersionUID = 1L;

	/**
	 * 版本号
	 * 目前版本“1.0.0”
	 */
	private String version;
	
	/**
	 * 平台流水号（平台返回）
	 */
	private String smzfMsgId;
	
	/**
	 * 请求日期时间
	 * 格式为yyyyMMddHHmmss
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime reqDate;
	
	/**
	 * 应答日期时间
	 * 格式为yyyyMMddHHmmss
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime respDate;
	
	/**
	 * 应答类型
	 * S: 成功
	 * E: 失败
	 * R: 不确定（处理中）
	 */
	private String respType;
	
	/**
	 * 应答码
	 * 成功：000000
	 * 补单成功：000090
	 * 失败：返回具体的响应码。
	 */
	private String respCode;
	
	/**
	 * 应答描述
	 */
	private String respMsg;
	
	/**
	 * 备用域 1
	 */
	private String extend1;
	/**
	 * 备用域 2
	 */
	private String extend2;
	
	/**
	 * 备用域 3
	 */
	private String extend3;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSmzfMsgId() {
		return smzfMsgId;
	}

	public void setSmzfMsgId(String smzfMsgId) {
		this.smzfMsgId = smzfMsgId;
	}	

	public LocalDateTime getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDateTime reqDate) {
		this.reqDate = reqDate;
	}

	public LocalDateTime getRespDate() {
		return respDate;
	}

	public void setRespDate(LocalDateTime respDate) {
		this.respDate = respDate;
	}

	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
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
	
	public static FftResult error(){
		FftResult result = new FftResult();
		result.setRespCode(RespCode.ERROR.getCode());
		result.setRespDate(LocalDateTime.now());
		result.setRespMsg("订单系统异常");
		return result;
	}
	
}
