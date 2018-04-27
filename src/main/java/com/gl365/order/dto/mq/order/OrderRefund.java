package com.gl365.order.dto.mq.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderRefund {
	
    /**
     * 退款单号
     */
    private String orderSn;
    /**
     * 退款单标题
     */
    private String orderTitle;

    /**
     * 单状态 5退货，7超时作废，8, 退款进行中
     */
    private Integer orderStatus;

    /**
     * 退款总金额
     */
    private BigDecimal totalAmount;

    /**
     * 退款豆子
     */
    private BigDecimal beanAmount;

    /**
     * 退款现金
     */
    private BigDecimal cashAmount;

    /**
     * 收款人id
     */
    private String memberId;

    /**
     * 商户号
     */
    private String merchantNo;
    

    /**
     * 退款操作人id
     */
    private String refundOperatorId;

    /**
     * 收银操作人id
     */
    private String operatorId;

    /**
     * 退款原始单号
     */
    private String origOrderSn;

    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     *  渠道
     */
    private String channel;
    
    /**
     * 返利金额(返乐豆)
     */
    private BigDecimal giftAmount;
    
    /**
     * 支付确认支付时间
     */
    private String paymentTime;
    
	/**
	 * 不可返利金额
	 */
	private BigDecimal noBenefitAmount;
	
    /**
     * 设备编号
     */
    private String terminal;

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public BigDecimal getNoBenefitAmount() {
		return noBenefitAmount;
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	public String getPaymentTime() {
		return paymentTime;
	}
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime =  paymentTime.format(formatter);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRefundOperatorId() {
		return refundOperatorId;
	}

	public void setRefundOperatorId(String refundOperatorId) {
		this.refundOperatorId = refundOperatorId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBeanAmount() {
		return beanAmount;
	}

	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOrigOrderSn() {
		return origOrderSn;
	}

	public void setOrigOrderSn(String origOrderSn) {
		this.origOrderSn = origOrderSn;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}



}
