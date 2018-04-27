package com.gl365.order.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl365.order.common.enums.PaymentOrderTypeEnum;
import com.gl365.order.handler.AbstractPaymentHandler;
import com.gl365.order.handler.PaymentHandler;
import com.gl365.order.mq.consumer.OrderConsumer;

/**
 * < 交易确认 >
 * 
 * @since hui.li 2017年5月26日 下午6:28:25
 */
public class PaymentSucceeHandlerImpl extends AbstractPaymentHandler {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentSucceeHandlerImpl.class);
	@Override
	public PaymentHandler execute() {
		if (PaymentOrderTypeEnum.NORMAL.getValue().equals(orderType)
				|| PaymentOrderTypeEnum.EXPERT.getValue().equals(orderType)
				|| PaymentOrderTypeEnum.ONLINE_SHOPPING.getValue().equals(orderType)) {
			/* 保存商家初始评论 */
			saveMerchantComment();
			/* 保存打赏初始评论 */
			savePersonalComment();
		} else if(PaymentOrderTypeEnum.GROUP_PAY.getValue().equals(orderType)){
			/* 群买单完成新增主单评论 */
			LOG.info("PaymentOrderTypeEnum saveGroupPayComment");
			saveGroupPayComment();
			/* 保存打赏初始评论 */
			LOG.info("PaymentOrderTypeEnum savePersonalComment start");
			savePersonalComment();
			LOG.info("PaymentOrderTypeEnum savePersonalComment end");
		}else if (PaymentOrderTypeEnum.REWARD.getValue().equals(orderType))
			/* 完成会员打赏评论 */
			donePersonalComment();
		else
			;
		return this;
	}

}
