package com.gl365.order.remote.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
public class WxConfirmReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 机构代码
	 */
	private String organCode;
	/**
	 * 给乐订单号
	 */
	private String merchantOrderNo;
	/**
	 * 融脉订单号
	 */
	private String organOrderNo;
	/**
	 * 通道订单号
	 */
	private String transactionId;
	/**
	 * 现金交易金额
	 */
	private BigDecimal cashAmount;
	/**
	 * 支付结果
	 */
	private String payResult;
	/**
	 * 支付描述
	 */
	private String payDesc;
	/**
	 * 抵扣金额
	 */
	private BigDecimal decAmount;
	/**
	 * decResult
	 */
	private String decResult;
	/**
	 * 付款银行
	 */
	private String 	bankType;
	/**
	 * 交易时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime organPayTime;
	
	
	

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

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
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

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
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

	public LocalDateTime getOrganPayTime() {
		return organPayTime;
	}

	public void setOrganPayTime(LocalDateTime organPayTime) {
		this.organPayTime = organPayTime;
	}

}
