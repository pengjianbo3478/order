package com.gl365.order.dto.req.customer;

import java.util.List;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.TransCode;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户新增终端请求(MER4)请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
public class FftMER04 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "商户进件", required = false, example="0")
	private String transCode =TransCode.MER04.getValue(); 
	
	@ApiModelProperty(value = "付费通商户号", required = true)
	private String orgCode;
	
	@ApiModelProperty(value = "终端信息域", required = true)
	private List<TidInfo> tidInfos;

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<TidInfo> getTidInfos() {
		return tidInfos;
	}

	public void setTidInfos(List<TidInfo> tidInfos) {
		this.tidInfos = tidInfos;
	}

}
