package com.gl365.order.dto.comment.command;

public class GetScoreDetailed4MerchantCommand {

	private String commentType;// 评价类型 0全部 1好评 2中评 3差评

	private Integer curPage;// 待查询的当前页

	private Integer pageSize;// 每页显示数量，默认10

	private String merchantNo;// 商户编号

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
