package com.gl365.order.dto.mq.push.app;

public class SystemContent {

	private String systemType;

	private SystemBody systemBody;

	public SystemContent(String systemType, SystemBody systemBody) {
		this.systemBody = systemBody;
		this.systemType = systemType;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public SystemBody getSystemBody() {
		return systemBody;
	}

	public void setSystemBody(SystemBody systemBody) {
		this.systemBody = systemBody;
	}

}
