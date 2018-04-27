package com.gl365.order.dto.req.merchant;

import java.util.ArrayList;
import java.util.List;

public class GetMerchantDetail4MerchantReq {
	/**
	 * 商家编号 , 支持批量
	 */
	private List<String> merchantNo;

	public GetMerchantDetail4MerchantReq(){
		super();
	}
	
	public GetMerchantDetail4MerchantReq(String merchantNo) {
		super();
		this.merchantNo = new ArrayList<>();
		this.merchantNo.add(merchantNo);
	}

	public List<String> getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(List<String> merchantNo) {
		this.merchantNo = merchantNo;
	}
}
