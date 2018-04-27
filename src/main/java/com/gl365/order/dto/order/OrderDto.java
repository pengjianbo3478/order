package com.gl365.order.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDto  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7709974720125434681L;

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
     * 支付场景
     */
    private Integer paymentConfig;

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
     * mq推送时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime pushTime;

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
     * 认领金额
     */
    private BigDecimal aloneAmount;

    
    /**
     * 群id
     */
    private String groupId;
    
    /**
     * 主单状态
     */
    private Integer  mainOrderStatus;
    
    /**
     *  渠道
     */
    private String channel;
    
    //--------------------------微信添加字段---------------------------
    
    private String  tokenId;
    
	/**
	 * 支付通道标志
	 */
	private String payChannel;
	
	private String payInfo;
	
    /**
     * 群主应付金额
     */
    private BigDecimal groupMainuserPay;
    
	/**
	 * 不可返利金额
	 */
	private BigDecimal noBenefitAmount;
    
	public BigDecimal getNoBenefitAmount() {
		
		return noBenefitAmount==null?new BigDecimal(0):noBenefitAmount;
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
    
    public String getMerchantNo() {
		return merchantNo;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
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

    public Integer getPaymentConfig() {
        return paymentConfig;
    }

    public void setPaymentConfig(Integer paymentConfig) {
        this.paymentConfig = paymentConfig;
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

	public LocalDateTime getPushTime() {
		return pushTime;
	}

	public void setPushTime(LocalDateTime pushTime) {
		this.pushTime = pushTime;
	}

	public BigDecimal getAloneAmount() {
		return aloneAmount;
	}

	public void setAloneAmount(BigDecimal aloneAmount) {
		this.aloneAmount = aloneAmount;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getMainOrderStatus() {
		return mainOrderStatus;
	}

	public void setMainOrderStatus(Integer mainOrderStatus) {
		this.mainOrderStatus = mainOrderStatus;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public BigDecimal getGroupMainuserPay() {
		return groupMainuserPay;
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}
    
    
}