package com.gl365.order.service;

import java.util.List;

import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.OrderListSum;
import com.gl365.order.dto.order.command.QueryOrderCommand;

public interface QueryOrderService {
	/**
	 * 按条件查询订单
	 * @param command
	 * @return
	 */
	public PageDto<OrderDto> selectOrderList(QueryOrderCommand command, int pageNo, int pageSize);
	
	/**
	 * 按条件查询订单合计
	 * @param command
	 * @return
	 */
	public ResultDto<OrderListSum> selectOrderListSum(QueryOrderCommand command);
	
	/**
	 * 群买单查询。
	 * @param groupId
	 * @return
	 */
	public List<OrderDto> getByOrderListGroupId(String groupId);
	

}
