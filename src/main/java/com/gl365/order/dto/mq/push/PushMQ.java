package com.gl365.order.dto.mq.push;

/**
 * @author DELL
 *
 */
public class PushMQ {
	/**
	 * 用户ID 用户对应userId 商户对应merchantNo
	 */
	private String uid;

	private String content;

	private String app;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
