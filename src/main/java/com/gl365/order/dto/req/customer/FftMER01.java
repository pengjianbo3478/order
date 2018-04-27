package com.gl365.order.dto.req.customer;

import java.math.BigDecimal;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.SceneCode;
import com.gl365.order.enums.TransCode;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通商户进件接口--支付查询请求(MER01)请求报文参数信息
 * @author dfs_523
 *2017年6月17日下午12:04:13
 */
public class FftMER01 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "商户进件", required = false, example="0")
	private String transCode =TransCode.MER01.getValue(); 
	
	@ApiModelProperty(value = "渠道方商户号（请求方系统商户编号）", required = true)
	private String outOrgCode;
	
	@ApiModelProperty(value = "MCC码", required = true)
	private String mcc;
	
	@ApiModelProperty(value = "地址编码", required = true)
	private String districtCode;
	
	@ApiModelProperty(value = "商户名称", required = true)
	private String merName;
	
	@ApiModelProperty(value = "商户简称", required = true)
	private String merOutName;
	
	@ApiModelProperty(value = "商户类型", required = true)
	private String merType;
	
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
	/**
	 * 交易权限组  第一位表示申请银行卡收单
	 *				第二位表示微信支付宝业务
	 *				第三位表示T0 
	 *				第四位表示快捷支付
	 *				后4位备用默认为0 如申请开通对应位设置为1，不申请设置为0：如11100000 不能全为0
	 */
	@ApiModelProperty(value = "交易权限组 ", required = true)
	private String transParams;
	
	@ApiModelProperty(value = "银行卡收单业务域", required = false)
	private PosBusiness posBusiness;
	
	@ApiModelProperty(value = "微信支付费率(十万级)，如千分之1为00100，申请T0业务时必填 ", required = false)
	private String wxFeeRate;
	
	@ApiModelProperty(value = "支付宝费率(十万级)，如千分之1为00100，申请T0业务时必填 ", required = false)
	private String zfbFeeRate;
	
	@ApiModelProperty(value = "T0手续费率(十万级)，如千分之1为00100，申请T0业务时必填 ", required = false)
	private String t0Rate;
	
	@ApiModelProperty(value = "快捷支付手续费率(十万级)，如千分之1为00100，申请快捷支付业务时必填 ", required = false)
	private String quickPayRate;
	
	@ApiModelProperty(value = "快捷支付单笔最低手续费（分） ", required = false)
	private BigDecimal quickPayMinFee;
	
	@ApiModelProperty(value = "营业执照号码（银行卡收单业务必填） ", required = false)
	private String licenseNo;
	
	@ApiModelProperty(value = "营业执照照片，Base64处理，不大于500k的jpg格式图片", required = false)
	private String licensePic;
	
	@ApiModelProperty(value = "营业执照注册地址（银行卡收单业务必填）", required = false)
	private String licenseAddr;
	
	@ApiModelProperty(value = "商户法定代表人姓名（银行卡收单业务必填）", required = false)
	private String personName;
	
	@ApiModelProperty(value = "商户法定代表人证件号（银行卡收单业务必填）", required = false)
	private String personNo;
	
	@ApiModelProperty(value = "法人证件有效期（银行卡收单业务必填）", required = false)
	private String personExp;
	
	@ApiModelProperty(value = "营业执照有效期（银行卡收单业务必填）", required = false)
	private String licenseExp;
	
	@ApiModelProperty(value = "税务登记证号码（三证合一可以为空）", required = false)
	private String taxNo;
	
	@ApiModelProperty(value = "组织机构代码证（三证合一可以为空）", required = false)
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

	public PosBusiness getPosBusiness() {
		return posBusiness;
	}

	public void setPosBusiness(PosBusiness posBusiness) {
		this.posBusiness = posBusiness;
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
