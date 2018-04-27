package com.gl365.order.dto.rsp.customer;

import java.math.BigDecimal;

import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.util.JsonUtil;

/**
 * 付费通扫码支付接口--条码支付消费请求(BARP01)响应报文参数信息
 */
public class FftBARP01Result extends FftResult {
	
	private static final long serialVersionUID = 1L;

	private String bankOrderId; //微信/支付宝订单号
	
	private BigDecimal buyerPayAmount; //付款金额
	
	private BigDecimal pointAmount;  //积分支付金额
	
	private String payTime; //交易支付时间 yyyyMMddHHmmss
	
	private String settleDate; //对账日期 yyyyMMdd
	
	private BigDecimal totalAmount; //订单总金额
	
	private String fftOrderId; //fft订单号

	public String getBankOrderId() {
		return bankOrderId;
	}

	public void setBankOrderId(String bankOrderId) {
		this.bankOrderId = bankOrderId;
	}

	public BigDecimal getBuyerPayAmount() {
		return buyerPayAmount;
	}

	public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}

	public BigDecimal getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFftOrderId() {
		return fftOrderId;
	}

	public void setFftOrderId(String fftOrderId) {
		this.fftOrderId = fftOrderId;
	}

	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}
}
