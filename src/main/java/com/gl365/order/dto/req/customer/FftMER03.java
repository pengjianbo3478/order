package com.gl365.order.dto.req.customer;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.SceneCode;
import com.gl365.order.enums.TransCode;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户修改请求(MER03)请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
public class FftMER03 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "商户进件", required = false, example="0")
	private String transCode =TransCode.MER03.getValue(); 
	
	@ApiModelProperty(value = "渠道方商户号（请求方系统商户编号）", required = true)
	private String outOrgCode;
	
	@ApiModelProperty(value = "付费通商户号", required = true)
	private String orgCode;
	
	@ApiModelProperty(value = "商户简称", required = true)
	private String merOutName;
	
	@ApiModelProperty(value = "商户法定代表人姓名", required = true)
	private String personName;
	
	@ApiModelProperty(value = "商户法定代表人证件号", required = true)
	private String personNo;
	
	@ApiModelProperty(value = "法人证件有效期", required = true)
	private String personExp;
	
	@ApiModelProperty(value = "营业执照有效期", required = true)
	private String licenseExp;
	
	@ApiModelProperty(value = "商户地址 ", required = true)
	private String merAddress;
	
	@ApiModelProperty(value = "商户联系人  ", required = true)
	private String merContact;
	//01-法人，02-实际控制人，03-代理人，00-其他
	@ApiModelProperty(value = "商户联系人类型  ", required = true)
	private String merContactType;
	
	@ApiModelProperty(value = "商户联系人身份证照片  ", required = true)
	private String merContactPic1;
	
	@ApiModelProperty(value = "商户联系人身份证照片  ", required = true)
	private String merContactPic2;
	
	@ApiModelProperty(value = "商户联系电话 ", required = true)
	private String merPhone;
	//商户结算账户类型（01对公， 02对私）
	@ApiModelProperty(value = "商户结算账户类型 ", required = true)
	private String accntType;
	
	@ApiModelProperty(value = "商户开户人身份证号 ", required = true)
	private String cardPersonNo;
	
	@ApiModelProperty(value = "开户行类型", required = true)
	private String bankBranchFlag;
	 
	@ApiModelProperty(value = "开户行名称", required = true)
	private String bankBranchName;
	 
	@ApiModelProperty(value = "开户行行号 ", required = true)
	private String bankBranchNo;
	
	@ApiModelProperty(value = "结算账户户名 ", required = true)
	private String accntName;
	
	@ApiModelProperty(value = "结算账户号码", required = true)
	private String accntNo;
	
	@ApiModelProperty(value = "微信支付费率 ", required = true)
	private String wxFeeRate;
	
	@ApiModelProperty(value = "支付宝费率 ", required = true)
	private String zfbFeeRate;
	
	@ApiModelProperty(value = "T0手续费率 ", required = true)
	private String t0Rate;
	
	@ApiModelProperty(value = "快捷支付手续费率 ", required = true)
	private String quickPayRate;
	
	
	@ApiModelProperty(value = "快捷支付单笔最低手续费 ", required = true)
	private String quickPayMinFee;


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


	public String getMerOutName() {
		return merOutName;
	}


	public void setMerOutName(String merOutName) {
		this.merOutName = merOutName;
	}


	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public String getPersonNo() {
		return personNo;
	}


	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}


	public String getPersonExp() {
		return personExp;
	}


	public void setPersonExp(String personExp) {
		this.personExp = personExp;
	}


	public String getLicenseExp() {
		return licenseExp;
	}


	public void setLicenseExp(String licenseExp) {
		this.licenseExp = licenseExp;
	}


	public String getMerAddress() {
		return merAddress;
	}


	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
	}


	public String getMerContact() {
		return merContact;
	}


	public void setMerContact(String merContact) {
		this.merContact = merContact;
	}


	public String getMerContactType() {
		return merContactType;
	}


	public void setMerContactType(String merContactType) {
		this.merContactType = merContactType;
	}


	public String getMerContactPic1() {
		return merContactPic1;
	}


	public void setMerContactPic1(String merContactPic1) {
		this.merContactPic1 = merContactPic1;
	}


	public String getMerContactPic2() {
		return merContactPic2;
	}


	public void setMerContactPic2(String merContactPic2) {
		this.merContactPic2 = merContactPic2;
	}


	public String getMerPhone() {
		return merPhone;
	}


	public void setMerPhone(String merPhone) {
		this.merPhone = merPhone;
	}


	public String getAccntType() {
		return accntType;
	}


	public void setAccntType(String accntType) {
		this.accntType = accntType;
	}


	public String getCardPersonNo() {
		return cardPersonNo;
	}


	public void setCardPersonNo(String cardPersonNo) {
		this.cardPersonNo = cardPersonNo;
	}


	public String getBankBranchFlag() {
		return bankBranchFlag;
	}


	public void setBankBranchFlag(String bankBranchFlag) {
		this.bankBranchFlag = bankBranchFlag;
	}


	public String getBankBranchName() {
		return bankBranchName;
	}


	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}


	public String getBankBranchNo() {
		return bankBranchNo;
	}


	public void setBankBranchNo(String bankBranchNo) {
		this.bankBranchNo = bankBranchNo;
	}


	public String getAccntName() {
		return accntName;
	}


	public void setAccntName(String accntName) {
		this.accntName = accntName;
	}


	public String getAccntNo() {
		return accntNo;
	}


	public void setAccntNo(String accntNo) {
		this.accntNo = accntNo;
	}


	public String getWxFeeRate() {
		return wxFeeRate;
	}


	public void setWxFeeRate(String wxFeeRate) {
		this.wxFeeRate = wxFeeRate;
	}


	public String getZfbFeeRate() {
		return zfbFeeRate;
	}


	public void setZfbFeeRate(String zfbFeeRate) {
		this.zfbFeeRate = zfbFeeRate;
	}


	public String getT0Rate() {
		return t0Rate;
	}


	public void setT0Rate(String t0Rate) {
		this.t0Rate = t0Rate;
	}


	public String getQuickPayRate() {
		return quickPayRate;
	}


	public void setQuickPayRate(String quickPayRate) {
		this.quickPayRate = quickPayRate;
	}


	public String getQuickPayMinFee() {
		return quickPayMinFee;
	}


	public void setQuickPayMinFee(String quickPayMinFee) {
		this.quickPayMinFee = quickPayMinFee;
	}
	
	
	 

}
