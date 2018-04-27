package com.gl365.order.dto.req.customer;


import java.math.BigDecimal;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.SceneCode;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通扫码支付接口--条码支付消费请求(BARP01)请求报文参数信息
 */
public class FftBARP01 extends FftHead {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "条码支付场景码", required = false, example="0")
	private String scene = SceneCode.BARP01.getValue(); 
	
	@ApiModelProperty(value = "商户在付费通注册的商户号", required = true)
	private String merchantCode;
	
	@ApiModelProperty(value = "条码", required = true)
	private String barCode;
	
	@ApiModelProperty(value = "订单金额(分)", required = true)
	private BigDecimal totalAmount;
	
	@ApiModelProperty(value = "订单标题", required = false)
	private String subject;
	
	@ApiModelProperty(value = "订单描述", required = false)
	private String desc;
	
	@ApiModelProperty(value = "商户操作员编号", required = false)
	private String operatorId;
	
	@ApiModelProperty(value = "商户门店编号", required = false)
	private String storeId;
	
	@ApiModelProperty(value = "商户机具终端编号", required = true)
	private String terminalId;
	
	@ApiModelProperty(value = "给乐订单号", required = true)
	private String merOrderId;

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

}
