package com.gl365.order.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.handler.PaymentHandler;

/**
 * < 交易异常处理 >
 * 
 * @since hui.li 2017年5月27日 下午3:30:37
 */
public class PaymentErrorHandlerImpl implements PaymentHandler {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentErrorHandlerImpl.class);

	@Override
	public PaymentHandler execute() {
		LOG.warn("订单异常》》》不支持的交易类型");
		return this;
	}

	@Override
	public void push() {
		return;
	}

	@Override
	public PaymentHandler build(PaymentMQ command) {
		return this;
	}

}
