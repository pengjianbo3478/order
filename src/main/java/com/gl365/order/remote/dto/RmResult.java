package com.gl365.order.remote.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"status","message","mchNo","errMsg","errCode","resultCode","sign"})
public class RmResult implements Serializable {
	private static final long serialVersionUID = -1783504146523309193L;

	// 返回状态码 0：成功、1：失败，此字段是通信标识
	private String status;

	// 返回信息
	private String message;
	/**
	 * errMsg : 签名错误
	 * errCode : signError
	 * resultCode : 1
	 */

	private String mchNo;
	private String errMsg;
	private String errCode;
	private String resultCode;


	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
