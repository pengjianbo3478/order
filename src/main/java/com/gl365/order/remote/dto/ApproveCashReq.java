package com.gl365.order.remote.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

public class ApproveCashReq  implements Serializable{
	private static final long serialVersionUID = 6819228592510557718L;

	@NotNull(message = "接口流水号不能为空")
	@Length(max = 32, message = "接口流水号不能大于32")
	@ApiModelProperty(value = "接口流水号", required = true, example = "13213434")
	private String pono;

	@NotNull(message = "业务平台用户不能为空")
	@Length(max = 50, message = "业务平台用户不能大于50")
	@ApiModelProperty(value = "业务平台用户", required = true, example = "68e971158a6142f49f1cc7f67968dbeb")
	private String puserNo;
	
	@NotNull(message = "申请编号不能为空")
	@ApiModelProperty(value = "申请编号", required = true, example = "13213434")
	private String applyNo;
	
	@NotNull(message = "时间不能为空")
	@ApiModelProperty(value = " 格 式 ：YYYYMMDDHHMMSS", required = true, example = "20170911110232")
	private String applyTime;

	@NotNull(message = "金额不能为空")
	@ApiModelProperty(value = "金额", required = true, example = "1")
	private BigDecimal amount;

	@NotNull(message = "手续费不能为空")
	@ApiModelProperty(value = "手续费", required = true, example = "1")
	private BigDecimal chargeAmount;
	
	@NotNull(message = "提现收款卡号不能为空")
	@ApiModelProperty(value = "提现收款卡号", required = true, example = "6226000055552222")
	private String accountNo;
	
	@NotNull(message = "提现收款卡号不能为空")
	@ApiModelProperty(value = "提现收款户名", required = true, example = "王二小")
	private String accountName;
	
	@NotNull(message = "审批结果不能为空")
	@ApiModelProperty(value = "1-通过，2-拒绝。", required = true, example = "1-通过，2-拒绝。")
	private String approvalStatus;

	@ApiModelProperty(value = "备注", required = true, example = "备注")
	private String rmk;
	
	

	public String getPono() {
		return pono;
	}



	public void setPono(String pono) {
		this.pono = pono;
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



	public String getApprovalStatus() {
		return approvalStatus;
	}



	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}



	public String getRmk() {
		return rmk;
	}



	public void setRmk(String rmk) {
		this.rmk = rmk;
	}



	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}

}
