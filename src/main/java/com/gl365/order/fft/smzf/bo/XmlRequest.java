package com.gl365.order.fft.smzf.bo;
import javax.xml.bind.annotation.XmlRootElement;

import com.gl365.order.dto.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 请求报文根元素+报文头
 */
@XStreamAlias("merchant")
@XmlRootElement(name="merchant")
public  class XmlRequest<T extends XmlBody> extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6349808506227849768L;
	@XStreamAlias("head")
	private XmlHead xmlHead = new XmlHead();
	
	@XStreamAlias("body")
	private T xmlBody ;

	public XmlRequest(Class<T> cls) {
		try {
			this.xmlBody = cls.newInstance();
		} catch (Exception  e) {
			e.printStackTrace();
		}
	}
	
	public XmlRequest() {
		super();
	}

	public XmlHead getXmlHead() {
		return xmlHead;
	}

	public void setXmlHead(XmlHead xmlHead) {
		this.xmlHead = xmlHead;
	}

	public T getXmlBody() {
		return xmlBody;
	}

	public void setXmlBody(T xmlBody) {
		this.xmlBody = xmlBody;
	}
	
}
