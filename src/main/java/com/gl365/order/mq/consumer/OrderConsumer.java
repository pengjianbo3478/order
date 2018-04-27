package com.gl365.order.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.aliyun.ons.OnsListener;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.handler.FactoryPaymentHandler;
import com.gl365.order.quickpayment.service.FftOrderService;
import com.gl365.order.service.OrderService;
import com.google.gson.Gson;

@Component("payment-order-consumer")
public class OrderConsumer implements OnsListener {

	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

	// 消息类型红包
	// private static final String MESSAGE_TYPE_REDEN = "rp".toLowerCase();

	// 网关没通 暂时用N代替
	private static final String MESSAGE_TYPE_REDEN = "N".toLowerCase();
	// 交易通知P
	// private static final String MESSAGE_TYPE_REDEN = "P".toLowerCase();

	@Autowired
	private FftOrderService orderService;

	@Autowired
	private FactoryPaymentHandler factoryPaymentHandler;

	@Override
	public void receive(byte[] source) {
		String message = new String(source);
		LOG.info("<mq-received>订单接收交易成功消费信息,message={}", message);
		try {
			PaymentMQ payment = newInstance(message);
			if (null == payment || null == payment.getPaymentBody() || !MESSAGE_TYPE_REDEN.equals(payment.getMsgCategory().toLowerCase())) {
				LOG.warn("<mq-received>订单接收交易成功消费信息警告		===>	参数非法,message={}", message);
				return;
			}
			orderService.mqUpdateOrderDetailed(payment);
			factoryPaymentHandler.distribute(payment.getTranType()).build(payment).execute();
		} catch (Exception e) {
			LOG.error("<mq-received>订单接收交易成功消费信息异常	===>	RedenvelopeConsumer.receive exception,e:{}", e);
			throw new RuntimeException(e);
		}
	}

	private PaymentMQ newInstance(String message) {
		return new Gson().fromJson(message, PaymentMQ.class);
	}

}
