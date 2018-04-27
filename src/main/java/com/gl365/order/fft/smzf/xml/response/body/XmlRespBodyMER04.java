package com.gl365.order.fft.smzf.xml.response.body;

import java.util.List;

import com.gl365.order.dto.req.customer.TidInfo;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class XmlRespBodyMER04 extends XmlBody {
	
	private static final long serialVersionUID = -307849208336478505L;
	/**
	 * 应答码
	 */
	private String respCode;
	/**
	 * 应答内容
	 */
	private String respMsg;
	
	private List<TidInfo> tidInfos;
	
	public List<TidInfo> getTidInfos() {
		return tidInfos;
	}
	public void setTidInfos(List<TidInfo> tidInfos) {
		this.tidInfos = tidInfos;
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
