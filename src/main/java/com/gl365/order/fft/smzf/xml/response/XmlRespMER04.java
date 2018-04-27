package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER04;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespMER04 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyMER04 xmlRespBodyMER04 = new XmlRespBodyMER04();

	
	public XmlRespBodyMER04 getXmlRespBodyMER04() {
		return xmlRespBodyMER04;
	}


	public void setXmlRespBodyMER04(XmlRespBodyMER04 xmlRespBodyMER04) {
		this.xmlRespBodyMER04 = xmlRespBodyMER04;
	}


	@Override
	public XmlRespBodyMER04 getXmlBody() {
		// TODO Auto-generated method stub
		return xmlRespBodyMER04;
	}


	

}
