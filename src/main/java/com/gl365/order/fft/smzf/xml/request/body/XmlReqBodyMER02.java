package com.gl365.order.fft.smzf.xml.request.body;


import com.gl365.order.enums.TransCode;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码商户查询请求 MER02
 */
@XStreamAlias("body")
public class XmlReqBodyMER02 extends XmlBody {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private String transCode =TransCode.MER02.getValue(); 
	
	private String outOrgCode;
	
	private String orgCode;

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getOutOrgCode() {
		return outOrgCode;
	}

	public void setOutOrgCode(String outOrgCode) {
		this.outOrgCode = outOrgCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
