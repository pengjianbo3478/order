package com.gl365.order.dto.mq;

import java.io.Serializable;
import java.util.UUID;

public class MQCommand implements Serializable {

	private static final long serialVersionUID = -7454954768123017678L;

	/**
	 * 避免因组件原因导致消息重复消费的消息标识
	 */
	private String messageId;

	public MQCommand() {
	}

	public MQCommand(boolean createMessageId) {
		if (createMessageId)
			this.messageId = UUID.randomUUID().toString();
	}

	public MQCommand(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
