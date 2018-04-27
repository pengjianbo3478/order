package com.gl365.order.dto.rsp.merchant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MerchantInfoDtoResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 717522611879025245L;

	private Long merchantId;

	private String merchantNo;

	private String parentMerchantNo;

	private String categoryId;

	/**
	 * 行业名称
	 */
	private String categoryName;

	private String merchantType;

	private String merchantName;

	private String merchantShortname;

	private Integer province;

	private Integer city;

	private Integer district;

	private Integer town;

	private Integer busAreaId;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private String businessLicenceNo;

	private String registeredAddress;

	private String businessAddress;

	private String masterName;

	private String masterCertNo;

	private String masterTel;

	private String officeTel;

	private String offficeContact;

	private String bailor;

	private String bailorCertNo;

	private String email;

	private String openBankName;

	private String openBankNo;

	private String bankAccountNo;

	private String bankAccountType;

	private String bankAccountName;

	private String clientShow;

	private String havePos;

	private BigDecimal saleRate;

	private String merchantDesc;

	private String specialty;

	private String mainImage;

	private String logoImage;

	private String busLine;

	private String businessTime;

	private Integer hotLevel;

	private String glContractNo;

	private Long orderNum;

	private String url;

	private String qq;

	private String landmark;

	private BigDecimal avgPrice;

	private String agentNo;

	private String marketerId;

	private String commKey;

	private BigDecimal glFeeRate;

	private String glFeeType;

	private BigDecimal devUserFeeRate;

	private String ldSale;

	private String status;

	private Integer operatorCount;

	private String remark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	private String createBy;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	private String modifyBy;

	/**
	 * 评论等级 取评论总分/评论次数
	 */
	private BigDecimal commentGrade;

	/**
	 * 商圈名称
	 */
	private String busAreaName;

	/**
	 * 商家图片
	 */
	private List<String> images;

	/**
	 * 商家服务
	 */
	private List<String> specialService;

	public BigDecimal getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(BigDecimal commentGrade) {
		this.commentGrade = commentGrade;
	}

	public List<String> getSpecialService() {
		return specialService;
	}

	public void setSpecialService(List<String> specialService) {
		this.specialService = specialService;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getParentMerchantNo() {
		return parentMerchantNo;
	}

	public void setParentMerchantNo(String parentMerchantNo) {
		this.parentMerchantNo = parentMerchantNo;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantShortname() {
		return merchantShortname;
	}

	public void setMerchantShortname(String merchantShortname) {
		this.merchantShortname = merchantShortname;
	}

	public Integer getBusAreaId() {
		return busAreaId;
	}

	public void setBusAreaId(Integer busAreaId) {
		this.busAreaId = busAreaId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMasterCertNo() {
		return masterCertNo;
	}

	public void setMasterCertNo(String masterCertNo) {
		this.masterCertNo = masterCertNo;
	}

	public String getMasterTel() {
		return masterTel;
	}

	public void setMasterTel(String masterTel) {
		this.masterTel = masterTel;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOffficeContact() {
		return offficeContact;
	}

	public void setOffficeContact(String offficeContact) {
		this.offficeContact = offficeContact;
	}

	public String getBailor() {
		return bailor;
	}

	public void setBailor(String bailor) {
		this.bailor = bailor;
	}

	public String getBailorCertNo() {
		return bailorCertNo;
	}

	public void setBailorCertNo(String bailorCertNo) {
		this.bailorCertNo = bailorCertNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getOpenBankNo() {
		return openBankNo;
	}

	public void setOpenBankNo(String openBankNo) {
		this.openBankNo = openBankNo;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getClientShow() {
		return clientShow;
	}

	public void setClientShow(String clientShow) {
		this.clientShow = clientShow;
	}

	public String getHavePos() {
		return havePos;
	}

	public void setHavePos(String havePos) {
		this.havePos = havePos;
	}

	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public String getMerchantDesc() {
		return merchantDesc;
	}

	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getBusLine() {
		return busLine;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getGlContractNo() {
		return glContractNo;
	}

	public void setGlContractNo(String glContractNo) {
		this.glContractNo = glContractNo;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getMarketerId() {
		return marketerId;
	}

	public void setMarketerId(String marketerId) {
		this.marketerId = marketerId;
	}

	public String getCommKey() {
		return commKey;
	}

	public void setCommKey(String commKey) {
		this.commKey = commKey;
	}

	public BigDecimal getGlFeeRate() {
		return glFeeRate;
	}

	public void setGlFeeRate(BigDecimal glFeeRate) {
		this.glFeeRate = glFeeRate;
	}

	public String getGlFeeType() {
		return glFeeType;
	}

	public void setGlFeeType(String glFeeType) {
		this.glFeeType = glFeeType;
	}

	public BigDecimal getDevUserFeeRate() {
		return devUserFeeRate;
	}

	public void setDevUserFeeRate(BigDecimal devUserFeeRate) {
		this.devUserFeeRate = devUserFeeRate;
	}

	public String getLdSale() {
		return ldSale;
	}

	public void setLdSale(String ldSale) {
		this.ldSale = ldSale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getTown() {
		return town;
	}

	public void setTown(Integer town) {
		this.town = town;
	}

	public Integer getHotLevel() {
		return hotLevel;
	}

	public void setHotLevel(Integer hotLevel) {
		this.hotLevel = hotLevel;
	}

	public Integer getOperatorCount() {
		return operatorCount;
	}

	public void setOperatorCount(Integer operatorCount) {
		this.operatorCount = operatorCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getBusAreaName() {
		return busAreaName;
	}

	public void setBusAreaName(String busAreaName) {
		this.busAreaName = busAreaName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}