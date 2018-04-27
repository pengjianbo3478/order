package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.CreateOrderCommand;

public interface TipOrderService {

	/**
	 * 打赏订单
	 * @param command
	 * @return
	 */
	public ResultDto pointsReward(CreateOrderCommand command);

}
