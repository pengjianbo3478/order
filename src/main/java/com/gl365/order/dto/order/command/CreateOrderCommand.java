package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.enums.ChannelEnum;

import io.swagger.annotations.ApiModelProperty;

public class  CreateOrderCommand implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 2024477179577543629L;


	/**
     * 订单标题
     */
    private String orderTitle;


    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 乐豆金额
     */
    private BigDecimal beanAmount;

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
	private LocalDateTime paymentTime=LocalDateTime.now();


//
//    /**
//     * mq推送时间
//     */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	private LocalDateTime pushTime;

//    /**
//     * 付费通序列号
//     */
//    private String fftSequence;
//
//    /**
//     * 付费通订单号
//     */
//    private String fftOrderSn;
    
    /**
     * 商户号
     */
    private String merchantNo;
    
//    /**
//     * 支付条码
//     */
//    private String barCode;
    
    /**
     * 设备编号
     */
    private String terminal;
    
    /**
     * 认领金额
     */
    private BigDecimal aloneAmount;
    

    /**
     * 群id
     */
    private String groupId;
    
    //----------------微信接入添加字段------------------------------
//    /**
//     * 订单编号
//     */
//    private String orderSn;
    /**
     * 订单类型
     */
    @ApiModelProperty("订单类型1：正常订单\n" +
            "2：打赏订单（现金）\n" +
            "3：达人订单\n" +
            "4：网购订单\n" +
            "5：乐豆打赏\n" +
            "6：C到C乐豆支付\n" +
            "7：群支付")
    private Integer orderType;
    
    /**
     *  渠道
     */
    private String channel;
    /**
     * 支付成功跳转页
     */
    private String callbackUrl;
    
    private String openId;
    
    @ApiModelProperty("打赏乐豆")
    private BigDecimal payLdMoney;
    /**
     * h5支付需要ip
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

//	public String getBarCode() {
//		return barCode;
//	}
//
//	public void setBarCode(String barCode) {
//		this.barCode = barCode;
//	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
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

//	public BigDecimal getCashAmount() {
//		return cashAmount;
//	}
//
//	public void setCashAmount(BigDecimal cashAmount) {
//		this.cashAmount = cashAmount;
//	}
//
//	public BigDecimal getGiftAmount() {
//		return giftAmount;
//	}
//
//	public void setGiftAmount(BigDecimal giftAmount) {
//		this.giftAmount = giftAmount;
//	}

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

//	public LocalDateTime getPushTime() {
//		return pushTime;
//	}
//
//	public void setPushTime(LocalDateTime pushTime) {
//		this.pushTime = pushTime;
//	}
//
//	public String getFftSequence() {
//		return fftSequence;
//	}
//
//	public void setFftSequence(String fftSequence) {
//		this.fftSequence = fftSequence;
//	}
//
//	public String getFftOrderSn() {
//		return fftOrderSn;
//	}
//
//	public void setFftOrderSn(String fftOrderSn) {
//		this.fftOrderSn = fftOrderSn;
//	}

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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public BigDecimal getPayLdMoney() {
		return payLdMoney;
	}

	public void setPayLdMoney(BigDecimal payLdMoney) {
		this.payLdMoney = payLdMoney;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp;
	}

//	public String getOrderSn() {
//		return orderSn;
//	}
//
//	public void setOrderSn(String orderSn) {
//		this.orderSn = orderSn;
//	}


    
}