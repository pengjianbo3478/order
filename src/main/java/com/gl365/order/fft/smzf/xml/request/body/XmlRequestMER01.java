package com.gl365.order.fft.smzf.xml.request.body;

import com.gl365.order.fft.smzf.bo.XmlRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("merchant")
public class XmlRequestMER01 extends XmlRequest{

	private static final long serialVersionUID = 5196996300041464543L;
	
	@XStreamAlias("body")
	private XmlReqBodyMER01 xmlBody = new XmlReqBodyMER01();

	public XmlReqBodyMER01 getXmlBody() {
		return xmlBody;
	}

	public void setXmlBody(XmlReqBodyMER01 xmlBody) {
		this.xmlBody = xmlBody;
	}
	
}
