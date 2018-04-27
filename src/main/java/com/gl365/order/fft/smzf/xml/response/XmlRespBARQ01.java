package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARQ01;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespBARQ01 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyBARQ01 xmlRespBodyBARQ01 = new XmlRespBodyBARQ01();

	public void setXmlBodyBARP01(XmlRespBodyBARQ01 xmlRespBodyBARQ01) {
		this.xmlRespBodyBARQ01 = xmlRespBodyBARQ01;
	}

	@Override
	public XmlBody getXmlBody() {
		return xmlRespBodyBARQ01;
	}
	
}
