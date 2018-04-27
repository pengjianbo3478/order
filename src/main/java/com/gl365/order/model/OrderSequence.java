package com.gl365.order.model;

import java.io.Serializable;
/**
 * 业务辅助，产生商户merchantNo流水号
 * @author blusewang
 *
 */
public class OrderSequence implements Serializable {
	private static final long serialVersionUID = 6986600707390403552L;
	private Long id;
	private int replaceKey=1;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getReplaceKey() {
		return replaceKey;
	}
	public void setReplaceKey(int replaceKey) {
		this.replaceKey = replaceKey;
	}
	
}