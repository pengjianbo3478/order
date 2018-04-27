package com.gl365.order.event;

import org.springframework.context.ApplicationEvent;

/**
 * 模拟审核提现
 * @author DELL
 *
 */
public class AuditCashEvent extends ApplicationEvent {
	
	private String cashJson;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuditCashEvent(Object source, String cashJson) {
		super(source);
		this.cashJson = cashJson;
	}

	public String getCashJson() {
		return cashJson;
	}
}
