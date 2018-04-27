package com.gl365.order.dto.rsp.customer;

import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.enums.TransCode;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespPosBusiness;

/**
 * 付费通商户查询请求(MER02)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftMER02Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;
	private String transCode =TransCode.MER02.getValue(); 
	//渠道方商户号
	private String outOrgCode;
	//付费通商户号
	private String orgCode;
	//MCC码
	private String mcc;
	//地区代码
	private String areaCode;
	//商户名称
	private String merName;
	//商户简称
	private String merOutName;
	//商户类型
	private String merType;
	//营业执照号码
	private String licenseNo;
	//营业执照注册地址
	private String licenseAddr;
	
	private String personName;
	
	private String personNo;
	
	private String personExp;
	
	private String licenseExp;
	
	private String taxNo;
	
	private String certNo;
	
	private String merAddress;
	
	//商户结算账户类型（01对公， 02对私）
	private String accntType;
	
	private String merContact;
	//01-法人，02-实际控制人，03-代理人，00-其他
	private String merContactType;
	
	private String merPhone;
	
	private String cardPersonNo;
	
	private String merContactPic1;
	
	private String merContactPic2;
	
	private String bankBranchFlag;
	 
	private String bankBranchName;
	 
	private String bankBranchNo;
	
	private String accntName;
	
	private String accntNo;
	
	private String transParams;
	
	private XmlRespPosBusiness posBusiness;

	/**
	 * 业务应答码
	 */
	private String rltCode;
	/**
	 * 业务应答内容
	 */
	private String rltMsg;
	
	
	public String getRltCode() {
		return rltCode;
	}


	public void setRltCode(String rltCode) {
		this.rltCode = rltCode;
	}


	public String getRltMsg() {
		return rltMsg;
	}


	public void setRltMsg(String rltMsg) {
		this.rltMsg = rltMsg;
	}


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

	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public XmlRespPosBusiness getPosBusiness() {
		return posBusiness;
	}


	public void setPosBusiness(XmlRespPosBusiness posBusiness) {
		this.posBusiness = posBusiness;
	}


	public String getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getLicenseNo() {
		return licenseNo;
	}


	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
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
