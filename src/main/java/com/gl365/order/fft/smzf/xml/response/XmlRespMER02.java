package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER02;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespMER02 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyMER02 xmlRespBodyMER02 = new XmlRespBodyMER02();

	public XmlRespBodyMER02 getXmlRespBodyMER02() {
		return xmlRespBodyMER02;
	}

	public void setXmlRespBodyMER02(XmlRespBodyMER02 xmlRespBodyMER02) {
		this.xmlRespBodyMER02 = xmlRespBodyMER02;
	}

	@Override
	public XmlRespBodyMER02 getXmlBody() {
		// TODO Auto-generated method stub
		return xmlRespBodyMER02;
	}

}
