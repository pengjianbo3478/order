package com.gl365.order.fft.smzf.xml.response.body;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class XmlRespBodyMER03 extends XmlBody {
	
	private static final long serialVersionUID = -307849208336478505L;
	
	
	/**
	 * 应答码
	 */
	private String transCode;
	/**
	 * 应答码
	 */
	private String respCode;
	/**
	 * 应答内容
	 */
	private String respMsg;
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
}
