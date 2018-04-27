package com.gl365.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.order.util.JsonUtil;

public class OrderCash implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cashId;

    private String puserNo;

    private String applyNo;

    private String applyTime;

    private BigDecimal amount;

    private BigDecimal chargeAmount;

    private String accountNo;

    private String accountName;

    private String rmk;

    private Integer status;

    private String auditor;

    public Long getCashId() {
        return cashId;
    }

    public void setCashId(Long cashId) {
        this.cashId = cashId;
    }

    public String getPuserNo() {
        return puserNo;
    }

    public void setPuserNo(String puserNo) {
        this.puserNo = puserNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
    

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
	
}