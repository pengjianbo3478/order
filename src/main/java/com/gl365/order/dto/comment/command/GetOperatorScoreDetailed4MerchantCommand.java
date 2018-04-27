package com.gl365.order.dto.comment.command;

/**
 * < 获取商家评价明细指令 >
 * 
 * @since hui.li 2017年5月12日 上午9:42:36
 */
public class GetOperatorScoreDetailed4MerchantCommand {

	private String commentType;// 评价类型 0全部 1好评 2中评 3差评

	private String operatorId;// 操作员ID

	private String merchantNo;// 商家编号

	private Integer curPage;// 待查询的当前页

	private Integer pageSize;// 每页显示数量，默认10

	public GetOperatorScoreDetailed4MerchantCommand() {

	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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
