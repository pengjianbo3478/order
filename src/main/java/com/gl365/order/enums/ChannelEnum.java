package com.gl365.order.enums;

public enum ChannelEnum {
	wx_pub("微信公众账号支付"), wx_qr("微信扫码支付"), wx_low("微信刷卡支付"), wx_app("微信 APP支付"), wx_wap("微信H5支付"), ali_qr(
			"支付宝扫码支付"), ali_low("支付宝刷卡支付"), ali_app("支付宝 APP支付"), ali_pub("支付宝服务窗支付"), ali_h5("支付宝 h5 支付"), quick_pay(
					"快捷支付"), gateway("网关支付"), payh5("H5 收银台"), c2d_pay("同名卡进出支付"), sign_pay("签约支付"),;

	private String text;

	private ChannelEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
