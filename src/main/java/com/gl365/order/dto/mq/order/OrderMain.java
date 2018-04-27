package com.gl365.order.dto.mq.order;

public class OrderMain {

	/**
	 *  refundIng 退款完成通知|refund 退款完成通知|confirm交易确认通知
	 */
	private String tranType;
	
	private  OrderRefund refund;
	
	private OrderConfirm confirm;

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public OrderRefund getRefund() {
		return refund;
	}

	public void setRefund(OrderRefund refund) {
		this.refund = refund;
	}

	public OrderConfirm getConfirm() {
		return confirm;
	}

	public void setConfirm(OrderConfirm confirm) {
		this.confirm = confirm;
	}
	
	
}
