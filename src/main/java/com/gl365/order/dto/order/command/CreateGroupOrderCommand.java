package com.gl365.order.dto.order.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.model.Order;

import io.swagger.annotations.ApiModelProperty;

public class  CreateGroupOrderCommand implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 2024477179577543629L;


	/**
     * 订单标题
     */
    private String orderTitle;
    /**
     * 群主应付金额
     */
    private BigDecimal groupMainuserPay;


    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;


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
     * 支付时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paymentTime=LocalDateTime.now();


    
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
     * 订单编号
     */
    private String orderSn;
    
    /**
     *  渠道
     */
    private String channel;
    /**
     * 支付成功跳转页
     */
    private String callbackUrl;
    
    private String openId;
    
    @ApiModelProperty("群支付商家号")//主单子单一样
    private String groupMerchantNo;
    
    /**
     * h5支付需要ip
     */
	private String mchCreateIp;
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


	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
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


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public BigDecimal getGroupMainuserPay() {
		return groupMainuserPay;
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	
	public static CreateGroupOrderCommand buildCreate(Order order,String callback,BigDecimal groupMainuser,String title){
		
		CreateGroupOrderCommand cgoc=new CreateGroupOrderCommand();
		
		if(null!=order.getAloneAmount()){
			cgoc.setAloneAmount(order.getAloneAmount());
		}
		
		cgoc.setChannel(order.getChannel());
		cgoc.setGroupId(order.getGroupId());
		cgoc.setMemberId(order.getMemberId());
		cgoc.setMerchantNo(order.getMerchantNo());
		if(null!=order.getOperatorId()){
			cgoc.setOperatorId(order.getOperatorId());
		}
		cgoc.setOrderSn(order.getOrderSn());
		if(null!=order.getOrderTitle()){
			cgoc.setOrderTitle(order.getOrderTitle());
		}
		cgoc.setPaymentConfig(order.getPaymentConfig());
		cgoc.setTotalAmount(order.getTotalAmount());
		
		cgoc.setCallbackUrl(callback);
		cgoc.setGroupMainuserPay(groupMainuser);
		cgoc.setOrderTitle(title);
		
		if(null!=order.getTerminal()){
			cgoc.setTerminal(order.getTerminal());
		}
		
		if(null!=order.getNoBenefitAmount()){
			cgoc.setNoBenefitAmount(order.getNoBenefitAmount());
		}
		return cgoc;
		
	}

	public String getGroupMerchantNo() {
		return groupMerchantNo;
	}

	public void setGroupMerchantNo(String groupMerchantNo) {
		this.groupMerchantNo = groupMerchantNo;
	}

	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp;
	}

    
}