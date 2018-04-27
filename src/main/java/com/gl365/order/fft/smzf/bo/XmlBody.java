package com.gl365.order.fft.smzf.bo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.gl365.order.dto.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码支付接口--body公共报文
 */
@XStreamAlias("body")
public class XmlBody extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2131784113509284042L;
	private String qrCode = "";
	private String extend1 = "";
	private String extend2 = "";
	private String extend3 = "";

	public String getQrCode() {
		return qrCode;
	}
	
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
}
