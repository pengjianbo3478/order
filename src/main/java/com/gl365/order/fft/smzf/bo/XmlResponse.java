package com.gl365.order.fft.smzf.bo;
import com.gl365.order.dto.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 响应报文 根元素+报文头
 */
@XStreamAlias("message")
public abstract class XmlResponse extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6096832729315774909L;
	@XStreamAlias("head")
	private XmlHead xmlHead = new XmlHead();

	
	public XmlHead getXmlHead() {
		return xmlHead;
	}

	public void setXmlHead(XmlHead xmlHead) {
		this.xmlHead = xmlHead;
	}
	
	public abstract XmlBody getXmlBody();

}
