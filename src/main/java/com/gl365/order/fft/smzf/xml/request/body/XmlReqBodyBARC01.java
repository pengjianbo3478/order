package com.gl365.order.fft.smzf.xml.request.body;


import java.math.BigDecimal;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码支付接口--条码支付（反扫）撤单请求 BARC01
 */
@XStreamAlias("body")
public class XmlReqBodyBARC01 extends XmlBody {
	
	private static final long serialVersionUID = -1562538289754172821L;

	private String merchantCode;
	
	private String terminalId;
	
	private BigDecimal totalAmount;
	
	private String barCode;

	private String oriSmzfMsgId;
	
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOriSmzfMsgId() {
		return oriSmzfMsgId;
	}

	public void setOriSmzfMsgId(String oriSmzfMsgId) {
		this.oriSmzfMsgId = oriSmzfMsgId;
	}

}
