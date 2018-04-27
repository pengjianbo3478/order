package com.gl365.order.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.order.enums.PaymentTypeEnum;
import com.gl365.order.handler.impl.PaymentBackHandlerImpl;
import com.gl365.order.handler.impl.PaymentErrorHandlerImpl;
import com.gl365.order.handler.impl.PaymentRecoverHandlerImpl;
import com.gl365.order.handler.impl.PaymentSucceeHandlerImpl;
import com.gl365.order.mq.producer.JPushProducer;
import com.gl365.order.mq.producer.MerchantCommentGradeProducer;
import com.gl365.order.service.CommentService;
import com.gl365.order.service.repo.OperatorInfoServiceRepo;

@Component
public class FactoryPaymentHandler {

	@Autowired
	JPushProducer jPushProducer;
	@Autowired
	CommentService commentService;
	@Autowired
	OperatorInfoServiceRepo operatorInfoServiceRepo;
	@Autowired
	MerchantCommentGradeProducer merchantCommentGradeProducer;

	public static PaymentTypeEnum EM;

	@SuppressWarnings("static-access")
	public PaymentHandler distribute(String payType) {
		AbstractPaymentHandler handler;
		if (typeContain(payType, EM.POS_XF, EM.WSXF, EM.DSZF, EM.SQQR))
			/*** POS消费、网上消费、打赏支付、预授权确认完成 */
			handler = new PaymentSucceeHandlerImpl();
		else if (typeContain(payType, EM.POS_XF_CZ, EM.WSXF_CZ, EM.POS_CX, EM.WSXF_CX, EM.SQQR_CZ, EM.POS_TH, EM.POS_BF_TH, EM.WSXF_TH, EM.WSXF_BF_TH))
			/*** POS消费冲正、网上消费冲正、POS撤销、网上消费撤销、授权确认撤销、POS部分退货、网上消费退货、网上消费部分退货 */
			handler = new PaymentBackHandlerImpl();
		else if (typeContain(payType, EM.POS_CX_CZ, EM.WSXF_CX_CZ, EM.POS_TH_CZ))
			/*** POS撤销冲正、网上消费撤销冲正、POS退货冲正 */
			handler = new PaymentRecoverHandlerImpl();
		else
			return new PaymentErrorHandlerImpl();
		handler.jPushProducer = this.jPushProducer;
		handler.commentService = this.commentService;
		handler.operatorInfoServiceRepo = this.operatorInfoServiceRepo;
		handler.merchantCommentGradeProducer = this.merchantCommentGradeProducer;
		return handler;
	}

	private boolean typeContain(String payType, PaymentTypeEnum... enums) {
		for (PaymentTypeEnum type : enums) {
			if (type.getValue().equals(payType))
				return true;
		}
		return false;
	}

}
