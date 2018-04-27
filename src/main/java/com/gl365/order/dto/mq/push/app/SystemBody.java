package com.gl365.order.dto.mq.push.app;

public class SystemBody {

	private String tranType;

	private AppBody tranBody;

	public SystemBody(String tranType, AppBody tranBody) {
		this.tranBody = tranBody;
		this.tranType = tranType;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public AppBody getTranBody() {
		return tranBody;
	}

	public void setTranBody(AppBody tranBody) {
		this.tranBody = tranBody;
	}

}
