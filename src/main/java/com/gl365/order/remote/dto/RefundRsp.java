package com.gl365.order.remote.dto;

import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;


public class RefundRsp extends RmResult{

	private static final long serialVersionUID = 1290008226392659658L;

	private String mchOrderNo;
	
	private String cpOrderNo;
	
	private String mchRefundNo;
	
	private String cpRefundNo;
	
	private BigDecimal refundAmt;
	
	private String refundResult; //;1--退款成功;2--退款处理中;3--退款失败
	
	
    /**
     * 设备编号
     */
    private String terminal;
    
    private String operator;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
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

	public String getCpRefundNo() {
		return cpRefundNo;
	}

	public void setCpRefundNo(String cpRefundNo) {
		this.cpRefundNo = cpRefundNo;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}
	
	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}

	public String getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
	}
	
}
