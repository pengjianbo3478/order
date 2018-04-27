package com.gl365.order.remote.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

public class RefundInfoBoReq implements Serializable{

	private static final long serialVersionUID = -3349375979032513022L;
	

	@ApiModelProperty(value = "支付渠道")
    private String channel;
	
	@ApiModelProperty(value = "商户退款单号 ", required = false)
	private String mchRefundNo;
	
	@ApiModelProperty(value = "平台退款单号 ", required = false)
	private String cpRefundNo;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMchRefundNo() {
		return mchRefundNo;
	}

	public void setMchRefundNo(String mchRefundNo) {
		this.mchRefundNo = mchRefundNo;
	}

	public String getCpRefundNo() {
		return cpRefundNo;
	}

	public void setCpRefundNo(String cpRefundNo) {
		this.cpRefundNo = cpRefundNo;
	}
	
	
}
