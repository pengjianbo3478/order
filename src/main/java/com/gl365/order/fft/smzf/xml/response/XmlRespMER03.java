package com.gl365.order.fft.smzf.xml.response;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyMER02;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARC01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARQ01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER03;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class XmlRespMER03 extends XmlResponse {
	
	private static final long serialVersionUID = -7995677565863089316L;
	
	@XStreamAlias("body")
	private XmlRespBodyMER03 xmlRespBodyMER03 = new XmlRespBodyMER03();



	public XmlRespBodyMER03 getXmlRespBodyMER03() {
		return xmlRespBodyMER03;
	}



	public void setXmlRespBodyMER03(XmlRespBodyMER03 xmlRespBodyMER03) {
		this.xmlRespBodyMER03 = xmlRespBodyMER03;
	}



	@Override
	public XmlRespBodyMER03 getXmlBody() {
		// TODO Auto-generated method stub
		return xmlRespBodyMER03;
	}

}
