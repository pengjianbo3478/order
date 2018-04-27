package com.gl365.order.service;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.req.gateway.NotifyCashResultsReq;
import com.gl365.order.dto.req.gateway.NotifyCashWithdrawalReq;

public interface CashOrderService {
	/**
	 * 保存提现通知
	 * @param req
	 * @return
	 */
	public ResultDto<?> saveNotifyCash(NotifyCashWithdrawalReq req);
	
	/**
	 * 修改提现状态
	 * @param req
	 * @return
	 */
	public ResultDto<?> updateNotifyCash(NotifyCashResultsReq req);
}
