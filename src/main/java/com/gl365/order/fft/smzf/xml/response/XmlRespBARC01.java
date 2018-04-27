package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARC01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARQ01;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespBARC01 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyBARC01 xmlRespBodyBARC01 = new XmlRespBodyBARC01();

	public void setXmlBodyBARP01(XmlRespBodyBARC01 xmlRespBodyBARC01) {
		this.xmlRespBodyBARC01 = xmlRespBodyBARC01;
	}

	@Override
	public XmlBody getXmlBody() {
		return xmlRespBodyBARC01;
	}
	
}
