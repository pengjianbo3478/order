package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class  QueryOrderCommand implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 2024477179577543629L;

    /**
     * 付款人id
     */
    private String memberId;

    /**
     * 支付场景
     */
    private Integer paymentConfig;

    /**
     * 支付时间
     */

	private LocalDate fromDate;
	

	private LocalDate toDate;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    
    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 渠道 wx_pub wx_h5
     */
    private String  channel;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    private BigDecimal totalAmountStart;
    
	private BigDecimal totalAmountEnd;

	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public Integer getPaymentConfig() {
		return paymentConfig;
	}


	public void setPaymentConfig(Integer paymentConfig) {
		this.paymentConfig = paymentConfig;
	}




	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getMerchantNo() {
		return merchantNo;
	}


	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getChannel() {
		return channel;
	}


	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	public BigDecimal getTotalAmountStart() {
		return totalAmountStart;
	}


	public void setTotalAmountStart(BigDecimal totalAmountStart) {
		this.totalAmountStart = totalAmountStart;
	}


	public BigDecimal getTotalAmountEnd() {
		return totalAmountEnd;
	}


	public void setTotalAmountEnd(BigDecimal totalAmountEnd) {
		this.totalAmountEnd = totalAmountEnd;
	}
}