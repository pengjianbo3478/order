package com.gl365.order.remote.dto;

import com.gl365.order.util.JsonUtil;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by zhouhui on 2017/9/14.
 */
public class OrderProfitReq  implements Serializable{

    /**
     * groupMainuserPay : 0
     * groupMerchantNo : string
     * groupOrderId : string
     * merchantNo : string
     * merchantOrderDesc : string
     * merchantOrderNo : string
     * merchantOrderTitle : string
     * noBenefitAmount : 0
     * orderType : string
     * organCode : string
     * organMerchantNo : string
     * requestDate : 2017-09-15
     * requestId : string
     * scene : string
     * splitFlag : string
     * totalAmount : 0
     * userId : string
     */

    @ApiModelProperty("群支付总金额")
    private BigDecimal groupMainuserPay;

    @ApiModelProperty("群支付商家号")//主单子单一样
    private String groupMerchantNo;

    @ApiModelProperty("群支付群号")
    private String groupOrderId;

    @ApiModelProperty("商家给乐商户号")//真正支付的商户号
    private String merchantNo ;

    @ApiModelProperty("订单描述")
    private String merchantOrderDesc;

    @ApiModelProperty("商户订单号")
    private String merchantOrderNo;

    @ApiModelProperty("订单标题")
    private String merchantOrderTitle;

    @ApiModelProperty("不可返利金额")
    private BigDecimal noBenefitAmount=new BigDecimal("0");

    @ApiModelProperty("订单类型1：正常订单\n" +
            "2：打赏订单（现金）\n" +
            "3：达人订单\n" +
            "4：网购订单\n" +
            "5：乐豆打赏\n" +
            "6：C到C乐豆支付\n" +
            "7：群支付")
    private String orderType;

    @ApiModelProperty(value = "机构代码",hidden = true)
    private String organCode ="10003";


    @ApiModelProperty(value = "请求日期",hidden = true,example ="20170915" )
    private String requestDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    @ApiModelProperty(value = "请求ID",hidden = true)
    private String requestId = UUID.randomUUID().toString();

    @ApiModelProperty("支付场景00：直接支付（快捷支付）\n" +
            "01：B扫C支付\n" +
            "02：C扫B支付\n" +
            "03：POS\n" +
            "04：微信H5支付")
    private String scene="04";

    @ApiModelProperty("分单标志0主单1子单")
    private String splitFlag;

    @ApiModelProperty("交易总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("会员号")
    private String userId;

	/**
	 * h5需要支付id
	 */
	private String mchCreateIp;
	
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

	public String getGroupMerchantNo() {
        return groupMerchantNo;
    }

    public void setGroupMerchantNo(String groupMerchantNo) {
        this.groupMerchantNo = groupMerchantNo;
    }

    public String getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(String groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantOrderDesc() {
        return merchantOrderDesc;
    }

    public void setMerchantOrderDesc(String merchantOrderDesc) {
        this.merchantOrderDesc = merchantOrderDesc;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getMerchantOrderTitle() {
        return merchantOrderTitle;
    }

    public void setMerchantOrderTitle(String merchantOrderTitle) {
        this.merchantOrderTitle = merchantOrderTitle;
    }


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }


    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(String splitFlag) {
        this.splitFlag = splitFlag;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getGroupMainuserPay() {
        return groupMainuserPay;
    }

    public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
        this.groupMainuserPay = groupMainuserPay;
    }

    public BigDecimal getNoBenefitAmount() {
        return noBenefitAmount;
    }

    public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
        this.noBenefitAmount = noBenefitAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}

	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp;
	}

}
