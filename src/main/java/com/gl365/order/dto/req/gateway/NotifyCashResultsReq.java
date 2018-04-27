package com.gl365.order.dto.req.gateway;

import java.io.Serializable;

import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

public class NotifyCashResultsReq   implements Serializable{
	private static final long serialVersionUID = 7085590894401451251L;
	


	
	@ApiModelProperty(value = "申请编号", required = true, example = "13213434")
	private String applyNo;
	
	@ApiModelProperty(value = "业务平台编号 格 式 ：YYYYMMDDHHMMSS", required = true, example = "20170911110232")
	private String applyTime;
	
	@ApiModelProperty(value = "业务平台用户", required = true, example = "13213434")
	private String puserNo;

	@ApiModelProperty(value = "1:-提现成功，2-提现失败。", required = true, example = "1")
	private String result;
	
	@ApiModelProperty(value = "备注", required = true, example = "备注")
	private String rmk;




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




	public String getPuserNo() {
		return puserNo;
	}




	public void setPuserNo(String puserNo) {
		this.puserNo = puserNo;
	}




	public String getResult() {
		return result;
	}




	public void setResult(String result) {
		this.result = result;
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
