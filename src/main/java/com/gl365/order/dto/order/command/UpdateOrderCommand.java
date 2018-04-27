package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateOrderCommand  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2287032804747868506L;

	/**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 乐豆金额
     */
    private BigDecimal beanAmount;

    /**
     * 现金金额
     */
    private BigDecimal cashAmount;

    /**
     * 返利金额(返乐豆)
     */
    private BigDecimal giftAmount;

    /**
     * 付款人id
     */
    private String memberId;


    /**
     * 操作员id
     */
    private String operatorId;

    /**
     * 被打赏人id
     */
    private String rewardUserId;

    /**
     * 被打赏原始单号
     */
    private String origOrderSn;

    /**
     * 支付时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paymentTime;



    /**
     * 付费通序列号
     */
    private String fftSequence;

    /**
     * 付费通订单号
     */
    private String fftOrderSn;
    
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付条码
     */
    private String barCode;

    /**
     * 设备编号
     */
    private String terminal;
  
    /**
     * 订单类型
     */
    private Integer orderType;

    public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}


	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRewardUserId() {
        return rewardUserId;
    }

    public void setRewardUserId(String rewardUserId) {
        this.rewardUserId = rewardUserId;
    }

    public String getOrigOrderSn() {
        return origOrderSn;
    }

    public void setOrigOrderSn(String origOrderSn) {
        this.origOrderSn = origOrderSn;
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

	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}
    
    
}