package com.gl365.order.dto.rsp.gateway;


/**
 * 融脉订单返回
 * @author DELL
 *
 */
public class QueryWXOrderRsq {
	
	/**
	 * 第三方订单号
	 */
	private String cpOrderNo;
	/**
	 * gl订单号
	 */
	private String mchOrderNo;
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	public String getMchOrderNo() {
		return mchOrderNo;
	}
	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

}
