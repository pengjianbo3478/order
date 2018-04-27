package com.gl365.order.dto.mq.wx;



import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by DELL on 2017/9/18.
 */
public class PayResultBo implements Serializable {

    private String organCode ;

    //商户订单号
    private String merchantOrderNo  ;

    //融脉订单号
    private String organOrderNo     ;

    //通道订单号
    private String transactionId    ;
    //现金交易金额
    private BigDecimal cashAmount    ;
    //支付结果
    private String payResult     ;

    //抵扣金额
    private BigDecimal decAmount      ;
    //抵扣结果
    private String decResult    ;//1成功 2失败
    //支付描述
    private String payDesc           ;
    //付款银行
    private String bankType          ;
    //交易时间
    private String organPayTime      ;


    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getOrganOrderNo() {
        return organOrderNo;
    }

    public void setOrganOrderNo(String organOrderNo) {
        this.organOrderNo = organOrderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public BigDecimal getDecAmount() {
        return decAmount;
    }

    public void setDecAmount(BigDecimal decAmount) {
        this.decAmount = decAmount;
    }

    public String getDecResult() {
        return decResult;
    }

    public void setDecResult(String decResult) {
        this.decResult = decResult;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getOrganPayTime() {
        return organPayTime;
    }

    public void setOrganPayTime(String organPayTime) {
        this.organPayTime = organPayTime;
    }
}
