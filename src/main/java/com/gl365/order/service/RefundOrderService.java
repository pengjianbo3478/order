package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.dto.order.command.RevokeOrderCommand;

public interface RefundOrderService {

	/**
	 * 退款
	 * @param command
	 * @return
	 */

	public ResultDto<?> refundOrder(OrderRefundCommand command);
	
	/**
	 * 订单撤销
	 * @param command
	 * @return
	 */
	public ResultDto<?>  updateRevokeByOrderSn(RevokeOrderCommand command);
	/**
	 * 退款fft
	 * @param command
	 * @return
	 */
	public ResultDto<?> refundOrderFft(OrderRefundCommand command);
	
	/**
	 * 强制修改单状态
	 * @param orderSn
	 * @return
	 */
	public int updateRefundByOrderSn(String orderSn);
}
