package com.gl365.order.dto;


import java.io.Serializable;

import com.gl365.order.util.JsonUtil;


public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000986504315325574L;

	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
	
}
