package com.gl365.order.dto.req.operator;

public class GetOperatorListReq {

	private String merchantNo;

	private Integer[] roldId;

	public GetOperatorListReq() {
		super();
	}

	public GetOperatorListReq(String merchantNo, Integer... roldId) {
		super();
		this.merchantNo = merchantNo;
		this.roldId = roldId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Integer[] getRoldId() {
		return roldId;
	}

	public void setRoldId(Integer[] roldId) {
		this.roldId = roldId;
	}

}
