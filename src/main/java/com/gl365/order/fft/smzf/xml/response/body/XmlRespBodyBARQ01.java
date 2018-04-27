package com.gl365.order.fft.smzf.xml.response.body;

import java.math.BigDecimal;

import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通扫码支付接口--条码支付（反扫）查询响应 BARQ01
 */
@XStreamAlias("body")
public class XmlRespBodyBARQ01 extends XmlBody {
	
	private static final long serialVersionUID = 7688138638943702761L;

	private String orderStatue; //3-订单有效，9-订单失效，4-订单支付中 5-订单成功支付 6-订单已撤销
	
	private String bankOrderId; //微信/支付宝订单号

	public String getOrderStatue() {
		return orderStatue;
	}

	public void setOrderStatue(String orderStatue) {
		this.orderStatue = orderStatue;
	}

	public String getBankOrderId() {
		return bankOrderId;
	}

	public void setBankOrderId(String bankOrderId) {
		this.bankOrderId = bankOrderId;
	}
	
}
