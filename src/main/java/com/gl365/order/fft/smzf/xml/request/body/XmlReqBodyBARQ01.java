package com.gl365.order.fft.smzf.xml.request.body;


import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码支付接口--条码支付（反扫）查询请求 BARQ01
 */
@XStreamAlias("body")
public class XmlReqBodyBARQ01 extends XmlBody {
	
	private static final long serialVersionUID = -1562538289754172821L;

	private String scene; 
	
	private String merchantCode;
	
	private String terminalId;
	
	private String barCode;

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

}
