package com.gl365.order.remote.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

public class RefundBoReq implements Serializable {

	private static final long serialVersionUID = -4394267355030042637L;
	
	@ApiModelProperty(value = "机构代码", required = true, example = "10003", notes = "微信：10003")
	private String organCode;
	
    private String channel;

	@ApiModelProperty(value = "商户订单号", required = false)
	private String mchOrderNo;
	
	@ApiModelProperty(value = "平台订单号", required = false)
	private String cpOrderNo;
	
	@ApiModelProperty(value = "退款订单号", required = true)
	private String mchRefundNo;
	
	@ApiModelProperty(value = "退款金额 ", required = true)
	private BigDecimal refundAmt;
	
	@ApiModelProperty(value = "退款说明 ", required = false)
	private String remark;
	
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

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public String getCpOrderNo() {
		return cpOrderNo;
	}

	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}

	public String getMchRefundNo() {
		return mchRefundNo;
	}

	public void setMchRefundNo(String mchRefundNo) {
		this.mchRefundNo = mchRefundNo;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
