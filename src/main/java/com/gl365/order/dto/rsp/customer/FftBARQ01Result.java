package com.gl365.order.dto.rsp.customer;

import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.util.JsonUtil;

/**
 * 付费通扫码支付接口--条码支付查询请求(BARQ01)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftBARQ01Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;

	private String orderStatue; //3-订单有效，9-订单失效，4-订单支付中 5-订单成功支付 6-订单已撤销
	
	private String BankOrderId; //微信/支付宝订单号

	public String getOrderStatue() {
		return orderStatue;
	}

	public void setOrderStatue(String orderStatue) {
		this.orderStatue = orderStatue;
	}

	public String getBankOrderId() {
		return BankOrderId;
	}

	public void setBankOrderId(String bankOrderId) {
		BankOrderId = bankOrderId;
	}

	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
	
}
