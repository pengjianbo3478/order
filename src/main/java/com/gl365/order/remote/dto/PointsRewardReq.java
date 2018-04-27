package com.gl365.order.remote.dto;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zhouhui on 2017/8/24.
 */
public class PointsRewardReq  {
    //机构代码
    @ApiModelProperty(hidden = true)
    private String organCode ;
    //    订单类型
//    5纯乐豆打赏6C对C纯乐豆支付
    @ApiModelProperty(value = "订单类型",notes = "5、纯乐豆打赏,6、C对C纯乐豆支付")
    private String orderType;

    //    给乐订单号
    @ApiModelProperty("给乐订单号")
    private String merchantOrderNo;

    //    请求交易日期
    @ApiModelProperty(hidden = true)
    private String requestDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    // 支付用户ID
    @ApiModelProperty("支付用户id")
    private String payUserId;

    //    消费金额
    @ApiModelProperty("乐豆数额")
    private BigDecimal totalAmount;

    //    接收用户ID
    @ApiModelProperty("被打赏用户id")
    private String rewardUserId;

    //     订单描述
    @ApiModelProperty("订单描述")
    private String merchantOrderDesc;

    //    被打赏原支付单号
    @ApiModelProperty("打赏原订单号")
    private String rewardPayId;

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRewardUserId() {
        return rewardUserId;
    }

    public void setRewardUserId(String rewardUserId) {
        this.rewardUserId = rewardUserId;
    }

    public String getMerchantOrderDesc() {
        return merchantOrderDesc;
    }

    public void setMerchantOrderDesc(String merchantOrderDesc) {
        this.merchantOrderDesc = merchantOrderDesc;
    }

    public String getRewardPayId() {
        return rewardPayId;
    }

    public void setRewardPayId(String rewardPayId) {
        this.rewardPayId = rewardPayId;
    }
}
