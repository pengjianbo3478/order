package com.gl365.order.service;

import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.dto.order.command.GetOrderSn4OrderCommand;
import com.gl365.order.dto.order.command.UpdateOrderCommand;

public interface OrderService {
	
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
	 * 根据状态群单查询
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageDto<OrderDto> getByOrderGroupIdCancelOrder(String groupId, String orderStatus, int pageNo, int pageSize);
	
	/**
	 * 单号查询主单
	 * @param orderSn
	 * @return
	 */
	public ResultDto<OrderDto> getOrderMainBySn(String orderSn);

	/**
	 * 获取gl订单号
	 * @param paymentConfig
	 * @param merchantNo
	 * @return
	 */
	public String getOrderSn(int paymentConfig,String merchantNo);
}
