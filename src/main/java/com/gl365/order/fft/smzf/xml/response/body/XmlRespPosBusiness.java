package com.gl365.order.fft.smzf.xml.response.body;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户银行卡收单业务域请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
@XStreamAlias("posBusiness")
@XmlRootElement(name ="posBusiness")
public class XmlRespPosBusiness implements Serializable{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "贷记卡手续费率", required = false)
	private String creditRate; 
	
	@ApiModelProperty(value = "借记卡手续费率", required = false)
	private String debitRate; 
	 
	@ApiModelProperty(value = "借记卡费封顶值", required = false)
	private String debitMaxValue; 
	
	@ApiModelProperty(value = "银联二维码支付费率", required = false)
	private String uPsmfFeeRate; 
	
	@ApiModelProperty(value = "营业开始时间", required = false)
	private String busiBeginTime; 
	
	@ApiModelProperty(value = "营业结束时间", required = false)
	private String busiEndTime; 
	
	@ApiModelProperty(value = "终端信息域", required = false)
	private List<XmlRespTidInfo> tidInfos;

	public String getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}

	public String getDebitRate() {
		return debitRate;
	}

	public void setDebitRate(String debitRate) {
		this.debitRate = debitRate;
	}

	public String getDebitMaxValue() {
		return debitMaxValue;
	}

	public void setDebitMaxValue(String debitMaxValue) {
		this.debitMaxValue = debitMaxValue;
	}

	public String getuPsmfFeeRate() {
		return uPsmfFeeRate;
	}

	public void setuPsmfFeeRate(String uPsmfFeeRate) {
		this.uPsmfFeeRate = uPsmfFeeRate;
	}

	public String getBusiBeginTime() {
		return busiBeginTime;
	}

	public void setBusiBeginTime(String busiBeginTime) {
		this.busiBeginTime = busiBeginTime;
	}

	public String getBusiEndTime() {
		return busiEndTime;
	}

	public void setBusiEndTime(String busiEndTime) {
		this.busiEndTime = busiEndTime;
	}

	public List<XmlRespTidInfo> getTidInfos() {
		return tidInfos;
	}

	public void setTidInfos(List<XmlRespTidInfo> tidInfos) {
		this.tidInfos = tidInfos;
	}


}
