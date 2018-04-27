package com.gl365.order.dto.req.customer;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.SceneCode;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通扫码支付接口--支付查询请求(BARQ01)请求报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:04:13
 */
public class FftBARQ01 extends FftHead{

	private static final long serialVersionUID = -6096195968534725502L;

	@ApiModelProperty(value = "条码支付场景码", required = false, example="0")
	private String scene = SceneCode.BARQ01.getValue(); 
	
	@ApiModelProperty(value = "商户在付费通注册的商户号", required = true)
	private String merchantCode;
	
	@ApiModelProperty(value = "商户机具终端编号", required = true)
	private String terminalId;
	
	@ApiModelProperty(value = "条码", required = true)
	private String barCode;

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

	public String getTerminalId() {
		return terminalId;
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

}
