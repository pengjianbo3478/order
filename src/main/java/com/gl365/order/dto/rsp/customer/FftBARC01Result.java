package com.gl365.order.dto.rsp.customer;

import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.util.JsonUtil;

/**
 * 付费通扫码支付接口--条码支付撤单请求(BARC01)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftBARC01Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;

	private String settleDate; //对账日期yyyyMMdd

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
	
}
