package com.gl365.order.dto.merchant;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author dfs_518
 * @ClassName: MerchantInfoDto
 * @Description: 商户基本信息Dto
 * 
 * @date 2017年5月18日 下午12:10:07
 *
 */
public class MerchantBasicsInfoDto implements Serializable {
	private static final long serialVersionUID = -1804103401396735815L;

	private String merchantNo;// 商户号

	private String merchantName;// 商家名称

	private String merchantShortName;// 商户简称

	private BigDecimal saleRate;// 返利率

	private String mainImage;// 商家主图

	private String logoImage;// 商家logo图片

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getMerchantShortName() {
		return merchantShortName;
	}

	public void setMerchantShortName(String merchantShortName) {
		this.merchantShortName = merchantShortName;
	}
	
	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	

}
