package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.enums.BeanRefundStatusEnum;
import com.gl365.order.remote.dto.CancelResultReq;
import com.gl365.order.remote.dto.RefundRsp;

public interface PaymentService {

	/**
	 * 乐豆退货接口
	 * @param req
	 * @param organCode
	 * @return
	 */
	ResultDto<?> beanRefund(RefundRsp req,String organCode);
	
	/**
	 * 乐豆退货结果查询接口
	 * @param req
	 * @return
	 */
	BeanRefundStatusEnum queryBeanRefundRlt(CancelResultReq req);
}
