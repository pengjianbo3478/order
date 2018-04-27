package com.gl365.order.fft.smzf.xml.response.body;

import java.math.BigDecimal;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码支付接口--条码支付（反扫）撤单响应 BARC01
 */
@XStreamAlias("body")
public class XmlRespBodyBARC01 extends XmlBody {
	
	private static final long serialVersionUID = 7688138638943702761L;

	private String settleDate; //对账日期yyyyMMdd

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

}
