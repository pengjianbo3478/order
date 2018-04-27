package com.gl365.order.dto.rsp.gateway;


/**
 * 融脉订单返回
 * @author DELL
 *
 */
public class createWXOrderRsq {
	
	/**
	 * 第三方订单号
	 */
	private String cpOrderNo;
	/**
	 * gl订单号
	 */
	private String mchOrderNo;

	/**
	 * 交易标志
	 */
	private String tokenId;

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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
}
