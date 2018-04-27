package com.gl365.order.remote.dto;

import java.math.BigDecimal;

import com.gl365.order.dto.order.command.CreateGroupOrderCommand;
import com.gl365.order.dto.order.command.CreateOrderCommand;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL on 2017/9/12.
 */
public class PrepayBoReq extends OrderProfitReq{

    // 支付渠道
    @ApiModelProperty(value = "支付渠道")
    private String channel;

    // 描述
    @ApiModelProperty("描述")
    private String description;

    // 为用户在商户 appid 下的唯一标识
    @ApiModelProperty(value = "用户微信openId",example = "osB3EwuXOp0adiqmJyMphymARJC0")
    private String openId;

    // 交易完成后跳转的 URL，需给绝对路径，255 字符内格 式如 :http://wap.tenpay.com/callback.asp 注
    // :该地址只作为前端页面的一个跳转， 需使用notifyUrl 通知结果作为支付最终结果。
    @ApiModelProperty("交易完成前端跳转页面地址")
    private String callbackUrl;

    @ApiModelProperty("打赏乐豆")
    private BigDecimal payLdMoney;

    @ApiModelProperty("被打赏员工会员号")
    private String rewardUserId;

    @ApiModelProperty("打赏原订单号")
    private  String rewardPayId;
    //操作人
    private String operator;
    
//	/**
//	 * 不可返利金额
//	 */
//	private BigDecimal noBenefitAmount;
//
//    public BigDecimal getNoBenefitAmount() {
//		return noBenefitAmount;
//	}
//
//	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
//		this.noBenefitAmount = noBenefitAmount;
//	}

	public BigDecimal getPayLdMoney() {
        return payLdMoney;
    }

    public void setPayLdMoney(BigDecimal payLdMoney) {
        this.payLdMoney = payLdMoney;
    }

    public String getRewardUserId() {
        return rewardUserId;
    }

    public void setRewardUserId(String rewardUserId) {
        this.rewardUserId = rewardUserId;
    }

    public String getRewardPayId() {
        return rewardPayId;
    }

    public void setRewardPayId(String rewardPayId) {
        this.rewardPayId = rewardPayId;
    }


    public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
    
    
    public static PrepayBoReq getPrepayBoReq(CreateOrderCommand order){
    	
    	PrepayBoReq req =new PrepayBoReq();
    	
    	req.setMerchantOrderDesc(order.getOrderTitle());
    	
    	req.setChannel(order.getChannel());

    	if(null!=order.getGroupId()){
    		req.setGroupOrderId(order.getGroupId());
    	}
        req.setMerchantNo(order.getMerchantNo());
        
        req.setMerchantOrderTitle(order.getOrderTitle());
        
     
        
        //分单标志0主单1子单
        if(order.getPaymentConfig().intValue()==5){
        	req.setSplitFlag("0");
        	req.setGroupMerchantNo(order.getMerchantNo());
        }
        if(order.getPaymentConfig().intValue()==6){
        	req.setSplitFlag("1");
        	req.setGroupMerchantNo(order.getMerchantNo());
        }
        req.setTotalAmount(order.getTotalAmount());
        
        req.setUserId(order.getMemberId());
        
        req.setDescription(order.getOrderTitle());
        
        if(null!=order.getOrigOrderSn()){
        //被打赏原始单号
        	req.setRewardPayId(order.getOrigOrderSn());
        }
        if(null!=order.getRewardUserId()){
        //被打赏人id
        	req.setRewardUserId(order.getRewardUserId());
        }
        if(null!=order.getOpenId()){
        	req.setOpenId(order.getOpenId());
        }
        
        if(null!=order.getNoBenefitAmount()){
        	req.setNoBenefitAmount(order.getNoBenefitAmount());
        }
        
        if(order.getPaymentConfig().intValue()==0||order.getPaymentConfig().intValue()==1||order.getPaymentConfig().intValue()==2){
        	req.setOrderType("1");//"订单类型1：正常订单\n"
        }else if(order.getPaymentConfig().intValue()==5||order.getPaymentConfig().intValue()==6){
        	req.setOrderType("7");
        }else if(order.getPaymentConfig().intValue()==10){
        	req.setOrderType("2");// "2：打赏订单（现金）\n" +
        	req.setPayLdMoney(order.getBeanAmount());//打赏乐豆金额
        }else if(order.getPaymentConfig().intValue()==9){
        	req.setOrderType("5");//"5：乐豆打赏\n" +
        	req.setPayLdMoney(order.getBeanAmount());
        }
        
    	req.setCallbackUrl(order.getCallbackUrl());
    	
        if(null!=order.getOperatorId()){
        	req.setOperator(order.getOperatorId());
        }
    	//req.setPayLdMoney(order.getBeanAmount());
        
        if(null!=order.getMchCreateIp()){
        	req.setMchCreateIp(order.getMchCreateIp());
        }
        
        if(null!=order.getTerminal()){
        	req.setTerminal(order.getTerminal());
        }

    	return req;
    }
    
    
    public static PrepayBoReq getPrepayBoReq(CreateGroupOrderCommand order){
    	
    	PrepayBoReq req =new PrepayBoReq();
    	
    	req.setOperator(order.getOperatorId());
    	
//    	req.setMerchantOrderNo(order.getOrderSn());
    	req.setMerchantOrderDesc(order.getOrderTitle());
    	
    	req.setChannel(order.getChannel());

    	if(null!=order.getGroupId()){
    		req.setGroupOrderId(order.getGroupId());
    	}
        req.setMerchantNo(order.getMerchantNo());
        
        req.setMerchantOrderTitle(order.getOrderTitle());
        
     
        
        //分单标志0主单1子单
        if(order.getPaymentConfig().intValue()==5){
        	req.setSplitFlag("0");
        	req.setGroupMerchantNo(order.getMerchantNo());
        }
        if(order.getPaymentConfig().intValue()==6){
        	req.setSplitFlag("1");
        	req.setGroupMerchantNo(order.getMerchantNo());
        }
        req.setTotalAmount(order.getTotalAmount());
        
        req.setUserId(order.getMemberId());
        
        req.setDescription(order.getOrderTitle());
        
        if(null!=order.getOpenId()){
        	req.setOpenId(order.getOpenId());
        }
        
        if(order.getPaymentConfig().intValue()==0||order.getPaymentConfig().intValue()==1||order.getPaymentConfig().intValue()==2){
        	req.setOrderType("1");//"订单类型1：正常订单\n"
        }else if(order.getPaymentConfig().intValue()==5||order.getPaymentConfig().intValue()==6){
        	req.setOrderType("7");
        }else if(order.getPaymentConfig().intValue()==10){
        	req.setOrderType("2");// "2：打赏订单（现金）\n" +
        }else if(order.getPaymentConfig().intValue()==9){
        	req.setOrderType("5");//"5：乐豆打赏\n" +
        }
        
    	req.setCallbackUrl(order.getCallbackUrl());
    	
    	if(null!=order.getGroupMainuserPay()){
    		req.setGroupMainuserPay(order.getGroupMainuserPay());
    	}
    	if(null!=order.getTerminal()){
    		req.setTerminal(order.getTerminal());
    	}
    	if(null!=order.getNoBenefitAmount()){
    		req.setNoBenefitAmount(order.getNoBenefitAmount());
    	}
    	return req;
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
