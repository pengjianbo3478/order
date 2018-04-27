package com.gl365.order.fft.smzf.xml.request.body;

import com.gl365.order.fft.smzf.bo.XmlRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("merchant")
public class XmlRequestMER04 extends XmlRequest{

	private static final long serialVersionUID = 3496697173524815861L;

	@XStreamAlias("body")
	private XmlReqBodyMER04 xmlBody = new XmlReqBodyMER04();

	public XmlReqBodyMER04 getXmlBody() {
		return xmlBody;
	}

	public void setXmlBody(XmlReqBodyMER04 xmlBody) {
		this.xmlBody = xmlBody;
	}
	
}
