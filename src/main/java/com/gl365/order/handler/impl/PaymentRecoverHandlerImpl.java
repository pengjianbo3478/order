package com.gl365.order.handler.impl;

import com.gl365.order.common.enums.PaymentOrderTypeEnum;
import com.gl365.order.handler.AbstractPaymentHandler;
import com.gl365.order.handler.PaymentHandler;

/**
 * < 交易撤销回滚-恢复>
 * 
 * @since hui.li 2017年5月26日 下午6:27:59
 */
public class PaymentRecoverHandlerImpl extends AbstractPaymentHandler {

	@Override
	public PaymentHandler execute() {
		if (PaymentOrderTypeEnum.NORMAL.getValue().equals(orderType) || PaymentOrderTypeEnum.EXPERT.getValue().equals(orderType) || PaymentOrderTypeEnum.ONLINE_SHOPPING.getValue().equals(orderType)) {
			recoverMerchantComment();
			unRemovePersonalComment();
		} else if (PaymentOrderTypeEnum.REWARD.getValue().equals(orderType))
			donePersonalComment();
		else
			;
		return this;
	}

}
