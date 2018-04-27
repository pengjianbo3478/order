package com.gl365.order.dto.req.merchant;

import com.gl365.order.dto.mq.MQCommand;

public class UpdateMerchantCommentCountReq extends MQCommand {

	private static final long serialVersionUID = -2916917032645946491L;

	private String merchantNo;

	private Integer count;

	private Integer score;

	public UpdateMerchantCommentCountReq() {
		super();
	}

	public UpdateMerchantCommentCountReq(String merchantNo, Integer score, Integer count) {
		super(true);
		this.merchantNo = merchantNo;
		this.score = score;
		this.count = count;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
