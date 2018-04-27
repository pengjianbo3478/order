package com.gl365.order.fft.smzf.xml.request.body;


import java.math.BigDecimal;

import com.gl365.order.dto.req.customer.PosBusiness;
import com.gl365.order.enums.TransCode;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通商户进件请求 MER01
 */
@XStreamAlias("body")
public class XmlReqBodyMER01 extends XmlBody {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private String transCode =TransCode.MER01.getValue(); 
	
	private String outOrgCode;
	
	private String mcc;
	
	private String districtCode;
	
	private String merName;
	
	private String merOutName;
	
	private String merType;
	
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
	/**
	 * 交易权限组  第一位表示申请银行卡收单
	 *				第二位表示微信支付宝业务
	 *				第三位表示T0 
	 *				第四位表示快捷支付
	 *				后4位备用默认为0 如申请开通对应位设置为1，不申请设置为0：如11100000 不能全为0
	 */
	private String transParams;
	
	private String wxFeeRate;
	
	private String zfbFeeRate;
	
	private String t0Rate;
	
	private String quickPayRate;
	
	private BigDecimal quickPayMinFee;
	
	private PosBusiness posBusiness;
	
	private String licenseNo;
	
	private String licensePic;
	
	private String licenseAddr;
	
	private String personName;
	
	private String personNo;
	
	private String personExp;
	
	private String licenseExp;
	
	private String taxNo;
	
	private String certNo;
	
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
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerOutName() {
		return merOutName;
	}
	public void setMerOutName(String merOutName) {
		this.merOutName = merOutName;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
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
	public String getTransParams() {
		return transParams;
	}
	public void setTransParams(String transParams) {
		this.transParams = transParams;
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
	public BigDecimal getQuickPayMinFee() {
		return quickPayMinFee;
	}
	public void setQuickPayMinFee(BigDecimal quickPayMinFee) {
		this.quickPayMinFee = quickPayMinFee;
	}
	public PosBusiness getPosBusiness() {
		return posBusiness;
	}
	public void setPosBusiness(PosBusiness posBusiness) {
		this.posBusiness = posBusiness;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicensePic() {
		return licensePic;
	}
	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}
	public String getLicenseAddr() {
		return licenseAddr;
	}
	public void setLicenseAddr(String licenseAddr) {
		this.licenseAddr = licenseAddr;
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
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
}
