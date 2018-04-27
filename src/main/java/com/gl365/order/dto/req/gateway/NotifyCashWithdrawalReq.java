package com.gl365.order.dto.req.gateway;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

public class NotifyCashWithdrawalReq  implements Serializable {
	private static final long serialVersionUID = 7085590894401451251L;
	


	@ApiModelProperty(value = "用户id", required = true, example = "13213434")
	private String puserNo;
	 
	@ApiModelProperty(value = "通知类型", required = true, example = "固定：drawAppl")
	private String type;
	
	@ApiModelProperty(value = "申请编号", required = true, example = "13213434")
	private String applyNo;
	
	@ApiModelProperty(value = "业务平台编号 格 式 ：YYYYMMDDHHMMSS", required = true, example = "20170911110232")
	private String applyTime;

	@ApiModelProperty(value = "金额", required = true, example = "1")
	private BigDecimal amount;

	@ApiModelProperty(value = "手续费", required = true, example = "1")
	private BigDecimal chargeAmount;
	
	@ApiModelProperty(value = "提现收款卡号", required = true, example = "6226000055552222")
	private String accountNo;
	
	@ApiModelProperty(value = "提现收款户名", required = true, example = "王二小")
	private String accountName;
	
	@ApiModelProperty(value = "备注", required = true, example = "备注")
	private String rmk;

	public String getPuserNo() {
		return puserNo;
	}

	public void setPuserNo(String puserNo) {
		this.puserNo = puserNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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


	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
	


}
