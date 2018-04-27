package com.gl365.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderRefund {
	/**
	 * 主键
	 */
    private String orderId;

    /**
     * 退款单号
     */
    private String orderSn;
    /**
     * 退款单标题
     */
    private String orderTitle;

    /**
     * 单类型
     */
    private Integer orderType;
    /**
     * 单状态（5, "网上消费退货"|6, "网上消费部分退货"）
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
     * 返利抵扣
     */
    private BigDecimal giftAmount;

    /**
     * 收款人id
     */
    private String memberId;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 退款原始单号
     */
    private String origOrderSn;

    private LocalDateTime paymentTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    /**
     * mq推送时间
     */
    private Date pushTime;

    private String fftSequence;

    private String fftOrderSn;

    private String barCode;

    private String terminal;
    
    /**
     * 查询时间
     */
	private LocalDateTime queryTime;
	/**
	 * 查询次数
	 */
	private int querySum;
	
    private Integer beanType;//扣豆状态
    
    /**
     *  渠道
     */
    private String channel;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
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

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public String getFftSequence() {
		return fftSequence;
	}

	public void setFftSequence(String fftSequence) {
		this.fftSequence = fftSequence;
	}

	public String getFftOrderSn() {
		return fftOrderSn;
	}

	public void setFftOrderSn(String fftOrderSn) {
		this.fftOrderSn = fftOrderSn;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public LocalDateTime getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(LocalDateTime queryTime) {
		this.queryTime = queryTime;
	}

	public int getQuerySum() {
		return querySum;
	}

	public void setQuerySum(int querySum) {
		this.querySum = querySum;
	}

	public Integer getBeanType() {
		return beanType;
	}

	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
    
}