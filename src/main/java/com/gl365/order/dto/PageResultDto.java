package com.gl365.order.dto;

import java.io.Serializable;

import com.gl365.order.common.ResultCodeEnum;

/**
 * < 基础响应 >
 * 
 * 包含常规请求响应的参数
 * 
 * @author hui.li 2017年4月12日 - 下午1:22:21
 * @Since 1.0
 */
public class PageResultDto<T> implements Serializable {

	private static final long serialVersionUID = 5598379493227689413L;

	/**
	 * result : 响应码
	 */
	private String result;

	/**
	 * description ： 响应描述
	 */
	private String description;

	/**
	 * pageData : 页码对象
	 */
	private PageDto<T> data;

	public PageResultDto() {
	}

	public PageResultDto(PageDto<T> data) {
		this.data = data;
	}

	/**
	 * 构造器 无响应数据一般适用于异常提示
	 * 
	 * @param result
	 */
	public PageResultDto(ResultCodeEnum.System result) {
		setResult(result.getCode());
		setDescription(result.getMsg());
		setData(null);
	}

	/**
	 * 构造器 使用枚举类的通知
	 * 
	 * @param result
	 * @param data
	 */
	public PageResultDto(ResultCodeEnum.System result, PageDto<T> data) {
		setResult(result.getCode());
		setDescription(result.getMsg());
		setData(data);
	}

	/**
	 * 构造器 使用枚举类的响应码和自定义提示
	 * 
	 * @param result
	 * @param message
	 * @param data
	 */
	public PageResultDto(ResultCodeEnum.System result, String message, PageDto<T> data) {
		setResult(result.getCode());
		setDescription(message);
		setData(data);
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

	public PageDto<T> getData() {
		return data;
	}

	public void setData(PageDto<T> data) {
		this.data = data;
	}

}
