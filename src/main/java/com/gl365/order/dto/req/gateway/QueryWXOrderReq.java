package com.gl365.order.dto.req.gateway;


/**
 * 融脉订单创建
 * @author DELL
 *
 */
public class QueryWXOrderReq {
	
	/**
	 * gl订单号
	 */
	private String mchOrderNo;

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	
	
}
