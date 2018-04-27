package com.gl365.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {
	/**
	 * id
	 */
    private String orderId;

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
    private LocalDateTime paymentTime;

    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

    /**
     * 修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

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
     * 查询时间
     */
	private LocalDateTime queryTime;
	/**
	 * 查询次数
	 */
	private int querySum;
    private String rmOrderNo; //rm单号
    
    private String transactionId;//通道订单号
    
    private String bankType;//付款银行
    
    private Integer beanType;//扣豆状态
	/**
	 * 抵扣金额
	 */
	private BigDecimal decAmount;
	
    /**
     *  渠道
     */
    private String channel;
    
    /**
     * 群主应付金额
     */
    private BigDecimal groupMainuserPay;

    
    
	/**
	 * decResult 抵扣结果 抵扣结果1成功 2失败
	 */
	private String decResult; 
	
	/**
	 * 乐豆扣除失败重试次数
	 */
	private int beanQuerySum;
	/**
	 * 支付通道标志
	 */
	private String payChannel;
	/**
	 * h5需要支付id
	 */
	private String mchCreateIp;
	/**
	 * 不可返利金额
	 */
	private BigDecimal noBenefitAmount;

    public BigDecimal getNoBenefitAmount() {
		return noBenefitAmount;
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

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

	public LocalDateTime getPushTime() {
		return pushTime;
	}

	public void setPushTime(LocalDateTime pushTime) {
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

	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
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

	public String getRmOrderNo() {
		return rmOrderNo;
	}

	public void setRmOrderNo(String rmOrderNo) {
		this.rmOrderNo = rmOrderNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getBeanType() {
		return beanType;
	}

	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	public BigDecimal getDecAmount() {
		return decAmount;
	}

	public void setDecAmount(BigDecimal decAmount) {
		this.decAmount = decAmount;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDecResult() {
		return decResult;
	}

	public void setDecResult(String decResult) {
		this.decResult = decResult;
	}

	public BigDecimal getGroupMainuserPay() {
		return groupMainuserPay;
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}

	public int getBeanQuerySum() {
		return beanQuerySum;
	}

	public void setBeanQuerySum(int beanQuerySum) {
		this.beanQuerySum = beanQuerySum;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp;
	}
    
    
}