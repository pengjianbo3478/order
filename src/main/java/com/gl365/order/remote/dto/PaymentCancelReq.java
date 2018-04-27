package com.gl365.order.remote.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.util.JsonUtil;

public class PaymentCancelReq implements Serializable{

	private static final long serialVersionUID = -3942927748949969364L;

	private String organCode;
	
	private String merchantOrderNo;
	
	private String organOrderNo;
	
	private String origMerchantOrderNo;
	
	private String origOrganOrderNo;
	
	private BigDecimal cashAmount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime organPayTime;
	
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

	public String getOrigMerchantOrderNo() {
		return origMerchantOrderNo;
	}

	public void setOrigMerchantOrderNo(String origMerchantOrderNo) {
		this.origMerchantOrderNo = origMerchantOrderNo;
	}

	public String getOrigOrganOrderNo() {
		return origOrganOrderNo;
	}

	public void setOrigOrganOrderNo(String origOrganOrderNo) {
		this.origOrganOrderNo = origOrganOrderNo;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public LocalDateTime getOrganPayTime() {
		return organPayTime;
	}

	public void setOrganPayTime(LocalDateTime organPayTime) {
		this.organPayTime = organPayTime;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
	
}
