package com.gl365.order.fft.smzf.bo;

public class FftRequestMessage extends FftMessage{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 回调url地址
	 */
	private String callBack;
	
	/**
	 * 商户号
	 */
	private String cooperator;
	
	/**
	 * <p>交易服务码</p>
	 */
	private String tranCode;
	
	private String cooperatortype;

	public FftRequestMessage() {
		super();
	}

	public FftRequestMessage(FftRequestMessage request) {
		super(request);
		this.callBack = request.getCallBack();
		this.cooperator = request.getCooperator();
		this.tranCode = request.getTranCode();
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getCooperatortype() {
		return "platform";
	}

}
