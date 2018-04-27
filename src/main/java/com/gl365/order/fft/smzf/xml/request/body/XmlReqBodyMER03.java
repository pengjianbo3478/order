package com.gl365.order.fft.smzf.xml.request.body;


import com.gl365.order.enums.TransCode;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通扫码商户修改请求 MER03
 */
@XStreamAlias("body")
public class XmlReqBodyMER03 extends XmlBody {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;


	private String transCode =TransCode.MER03.getValue(); 
	
	private String outOrgCode;
	
	private String orgCode;
	
	private String merOutName;
	
	private String personName;
	
	private String personNo;
	
	private String personExp;
	
	private String licenseExp;
	
	private String merAddress;
	
	private String merContact;
	//01-法人，02-实际控制人，03-代理人，00-其他
	private String merContactType;
	
	private String merContactPic1;
	
	private String merContactPic2;
	
	private String merPhone;
	//商户结算账户类型（01对公， 02对私）
	private String accntType;
	
	private String cardPersonNo;
	
	private String bankBranchFlag;
	 
	private String bankBranchName;
	 
	private String bankBranchNo;
	
	private String accntName;
	
	private String accntNo;
	
	private String wxFeeRate;
	
	private String zfbFeeRate;
	
	private String t0Rate;
	
	private String quickPayRate;
	
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
