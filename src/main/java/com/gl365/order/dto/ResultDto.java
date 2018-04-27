package com.gl365.order.dto;

import java.io.Serializable;

import com.gl365.order.common.ResultCodeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * < 基础结果DTO响应 >
 */
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = -8938751868672313574L;

	/**
	 * result : 响应码
	 */
	@ApiModelProperty(value = "响应码", required = true,example = "000000")
	private String result;

	/**
	 * description ： 响应描述
	 */
	@ApiModelProperty(value = "响应描述", required = false,example = "成功")
	private String description;

	/**
	 * data : 结果数据
	 */
	@ApiModelProperty(value = "结果数据", required = false,example = "{}")
	private T data;

	public ResultDto() {
	}

	public ResultDto(T data) {
		this.data = data;
	}

	public ResultDto(ResultCodeEnum.System source) {
		this.result = source.getCode();
		this.description = source.getMsg();
	}

	public ResultDto(ResultCodeEnum.System source, T data) {
		this.result = source.getCode();
		this.description = source.getMsg();
		this.data = data;
	}
	
	public ResultDto(String code, String msg, T data) {
		this.result = code;
		this.description = msg;
		this.data = data;
	}

	public ResultDto(ResultCodeEnum.System source, String message, T data) {
		this.result = source.getCode();
		this.description = message;
		this.data = data;
	}

	public static ResultDto<?> getErrInfo() {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
		result.setDescription(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ResultDto(ResultCodeEnum.Payment source) {
		this.result = source.getCode();
		this.description = source.getMsg();
	}

}
