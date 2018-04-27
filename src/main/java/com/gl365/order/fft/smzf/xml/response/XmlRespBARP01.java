package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARP01;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespBARP01 extends XmlResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4051089970521871408L;
	@XStreamAlias("body")
	private XmlRespBodyBARP01 xmlRspBodyBARP01 = new XmlRespBodyBARP01();

	public void setXmlBodyBARP01(XmlRespBodyBARP01 xmlRspBodyBARP01) {
		this.xmlRspBodyBARP01 = xmlRspBodyBARP01;
	}

	@Override
	public XmlBody getXmlBody() {
		return xmlRspBodyBARP01;
	}
	
}
