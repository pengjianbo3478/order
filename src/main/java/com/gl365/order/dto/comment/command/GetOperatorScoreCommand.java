package com.gl365.order.dto.comment.command;

public class GetOperatorScoreCommand {

	private String operatorId;

	private String merchantNo;

	public GetOperatorScoreCommand() {
		super();
	}

	public GetOperatorScoreCommand(String operatorId, String merchantNo) {
		super();
		this.operatorId = operatorId;
		this.merchantNo = merchantNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}
