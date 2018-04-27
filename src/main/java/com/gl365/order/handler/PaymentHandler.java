package com.gl365.order.handler;

import com.gl365.order.dto.mq.payment.PaymentMQ;

/**
 * < 处理付款成功后的逻辑 >
 * 
 * @since hui.li 2017年5月26日 下午6:21:55
 */
public interface PaymentHandler {

	/**
	 * Push
	 */
	public void push();

	/**
	 * Execute
	 * 
	 * @param TranBody
	 * @return
	 */
	public PaymentHandler execute();

	/**
	 * Build
	 * 
	 * @param PaymentMQ
	 */
	public PaymentHandler build(PaymentMQ command);

}
