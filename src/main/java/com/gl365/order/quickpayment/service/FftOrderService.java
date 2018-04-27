package com.gl365.order.quickpayment.service;

import java.util.List;

import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.dto.order.command.GetOrderSn4OrderCommand;
import com.gl365.order.dto.order.command.UpdateOrderCommand;

public interface FftOrderService {
	
	/**
	 * 
	 * @Title: getOrderSn
	 * @Description: 创建订单
	 * @param order
	 * @return
	 * @return: ResultDto<OrderDto>
	 */
	public ResultDto<?> createOrder(CreateOrderCommand command);
	/**
	 * 
	 * @Title: getOrderSn
	 * @Description: 修改订单
	 * @param order
	 * @return
	 * @return: ResultDto<OrderDto>
	 */
	public ResultDto<?> updateOrder(UpdateOrderCommand command);
	/**
	 * 
	 * @Title: createOrder
	 * @Description: 查询订单
	 * @param command
	 * @return
	 * @return: ResultDto<OrderDto>
	 */
	public ResultDto<OrderDto> getOrderBySn(GetOrderSn4OrderCommand command);
	/**
	 * 
	 * @Title: mqUpdateOrderDetailed
	 * @Description: 读取mq修改订单信息
	 * @param mq
	 * @return: void
	 */
	public void mqUpdateOrderDetailed(PaymentMQ mq);
	/**
	 * 查询未完成群买单
	 * @param command
	 * @return
	 */
	public ResultDto<OrderDto> getOrderByMemberId(String command);
	/**
	 * 主单查询
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageDto<OrderDto> getByOrderMainMemberId(String memberId, int pageNo, int pageSize);
	/**
	 * 子单查询
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageDto<OrderDto> getByOrderSonMemberId(String memberId, int pageNo, int pageSize);
	
	/**
	 * 群单查询
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageDto<OrderDto> getByOrderGroupId(String groupId, int pageNo, int pageSize);
	
	/**
	 * 单号查询主单
	 * @param orderSn
	 * @return
	 */
	public ResultDto<OrderDto> getOrderMainBySn(String orderSn);
}
