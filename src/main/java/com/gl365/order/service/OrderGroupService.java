package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.CreateGroupOrderCommand;

public interface OrderGroupService {
	
	/**
	 * 
	 * @Title: getOrderSn
	 * @Description: 创建订单
	 * @param order
	 * @return
	 * @return: ResultDto<OrderDto>
	 */
	public ResultDto<?> createGroupOrder(CreateGroupOrderCommand command);

	/**
	 * 群买单预支付
	 * @param command
	 * @return
	 */
	public ResultDto<?> createPayGroupOrder(CreateGroupOrderCommand command) ;
}
