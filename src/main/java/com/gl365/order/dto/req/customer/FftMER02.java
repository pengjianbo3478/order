package com.gl365.order.dto.req.customer;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.TransCode;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户查询请求(MER02)请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
public class FftMER02 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "交易类型", required = true)
	private String transCode =TransCode.MER02.getValue(); 
	
	@ApiModelProperty(value = "渠道方商户号", required = true)
	private String outOrgCode;
	
	@ApiModelProperty(value = "付费通商户号", required = true)
	private String orgCode;

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getOutOrgCode() {
		return outOrgCode;
	}

	public void setOutOrgCode(String outOrgCode) {
		this.outOrgCode = outOrgCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
