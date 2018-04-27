package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;

public interface OrderConfirmService {
	
	/**
	 * 交易确认扣豆
	 * @param orderSn
	 * @return
	 */
	public  ResultDto<?> confirm(String orderSn);
}
