package com.gl365.order.remote.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * < 基础响应 >
 * 
 * 包含常规请求响应的参数
 * 
 * @author hui.li 2017年4月12日 - 下午1:22:21
 * @Since 1.0
 */
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = 5598379493227689413L;

	/**
	 * result : 响应码
	 */
	@ApiModelProperty(value = "响应码", required = true, example = "000000")
	private String respCode;

	/**
	 * RespMsg ： 响应描述
	 */
	@ApiModelProperty(value = "响应描述", required = false, example = "成功")
	private String respMsg;

	/**
	 * data : 结果数据
	 */
	@ApiModelProperty(value = "结果数据", required = false, example = "")
	private T result;

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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
