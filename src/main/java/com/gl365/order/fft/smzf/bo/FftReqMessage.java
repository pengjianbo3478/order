package com.gl365.order.fft.smzf.bo;

public class FftReqMessage extends FftMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1645493568331476744L;
	
	/**
	 * 商户号
	 */
	private String cooperator;

	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}
	
}
