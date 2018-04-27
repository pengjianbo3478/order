package com.gl365.order.handler.impl;

import com.gl365.order.common.enums.PaymentOrderTypeEnum;
import com.gl365.order.handler.AbstractPaymentHandler;
import com.gl365.order.handler.PaymentHandler;

/**
 * < 交易回滚处理 >
 * 
 * @since hui.li 2017年5月26日 下午6:27:29
 */
public class PaymentBackHandlerImpl extends AbstractPaymentHandler {

	@Override
	public PaymentHandler execute() {
		if (PaymentOrderTypeEnum.NORMAL.getValue().equals(orderType) || PaymentOrderTypeEnum.EXPERT.getValue().equals(orderType) || PaymentOrderTypeEnum.ONLINE_SHOPPING.getValue().equals(orderType)) {
			backMerchantComment();
			removePersonalComment();
		} else if (PaymentOrderTypeEnum.REWARD.getValue().equals(orderType))
			backPersonalComment();
		else
			;
		return this;
	}

}
