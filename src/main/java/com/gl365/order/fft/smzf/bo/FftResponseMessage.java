package com.gl365.order.fft.smzf.bo;

public class FftResponseMessage extends FftMessage {
	private static final long serialVersionUID = 1L;
	
	/**
	 * <p>交易服务码</p>
	 */
	private String tranCode;
	
	public FftResponseMessage() {
		super();
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	
}
