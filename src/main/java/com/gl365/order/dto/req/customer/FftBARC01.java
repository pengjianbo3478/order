package com.gl365.order.dto.req.customer;

import java.math.BigDecimal;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.SceneCode;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通扫码支付接口--撤单请求(BARC01)请求报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:04:13
 */
public class FftBARC01 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "商户在付费通注册的商户号", required = true)
	private String merchantCode;
	
	@ApiModelProperty(value = "商户机具终端编号", required = true)
	private String terminalId;
	
	@ApiModelProperty(value = "原订单金额", required = true)
	private BigDecimal totalAmount;
	
	@ApiModelProperty(value = "条码", required = true)
	private String barCode;
	
	@ApiModelProperty(value = "付费通流水号", required = true)
	private String oriSmzfMsgId;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getOriSmzfMsgId() {
		return oriSmzfMsgId;
	}

	public void setOriSmzfMsgId(String oriSmzfMsgId) {
		this.oriSmzfMsgId = oriSmzfMsgId;
	}

}
