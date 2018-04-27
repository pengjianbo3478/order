package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARC01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARQ01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER01;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespMER01 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyMER01 xmlRespBodyMER01 = new XmlRespBodyMER01();

	public XmlRespBodyMER01 getXmlRespBodyMER01() {
		return xmlRespBodyMER01;
	}

	public void setXmlRespBodyMER01(XmlRespBodyMER01 xmlRespBodyMER01) {
		this.xmlRespBodyMER01 = xmlRespBodyMER01;
	}

	@Override
	public XmlRespBodyMER01 getXmlBody() {
		// TODO Auto-generated method stub
		return xmlRespBodyMER01;
	}

}
