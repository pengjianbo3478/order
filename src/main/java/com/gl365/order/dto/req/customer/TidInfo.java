package com.gl365.order.dto.req.customer;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.gl365.order.dto.req.FftHead;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户终端信息请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
@XStreamAlias("tidInfo")
public class TidInfo implements Serializable{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "终端号", required = false)
	private String tid; 
	/**
	 * 01-有线 02-无线（使用付费通SIM卡） 03-无线（不使用付费通SIM卡）
	 */
	@ApiModelProperty(value = "终端类型", required = false)
	private String tidType; 
	 
	@ApiModelProperty(value = "终端品牌", required = false)
	private String tidBrand; 
	
	@ApiModelProperty(value = "终端型号", required = false)
	private String tidModel; 
	
	@ApiModelProperty(value = "终端序列号", required = false)
	private String tidSn; 
	
	@ApiModelProperty(value = "装机地址", required = false)
	private String tidAddress; 
	
	@ApiModelProperty(value = "SIM卡号", required = false)
	private String simCard; 
	
	@ApiModelProperty(value = "流量费收取方式", required = false)
	private String chargeType; 
	
	@ApiModelProperty(value = "流量费", required = false)
	private String flowFee;
	/**
	 * 终端状态：00-未启用 01-正常 02-停用 99-注销
	 */
	@ApiModelProperty(value = "终端状态", required = false)
	private String status;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTidType() {
		return tidType;
	}

	public void setTidType(String tidType) {
		this.tidType = tidType;
	}

	public String getTidBrand() {
		return tidBrand;
	}

	public void setTidBrand(String tidBrand) {
		this.tidBrand = tidBrand;
	}

	public String getTidModel() {
		return tidModel;
	}

	public void setTidModel(String tidModel) {
		this.tidModel = tidModel;
	}

	public String getTidSn() {
		return tidSn;
	}

	public void setTidSn(String tidSn) {
		this.tidSn = tidSn;
	}

	public String getTidAddress() {
		return tidAddress;
	}

	public void setTidAddress(String tidAddress) {
		this.tidAddress = tidAddress;
	}

	public String getSimCard() {
		return simCard;
	}

	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getFlowFee() {
		return flowFee;
	}

	public void setFlowFee(String flowFee) {
		this.flowFee = flowFee;
	} 

}
