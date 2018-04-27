package com.gl365.order.dto.comment.command;

/**
 * < 获取评论模板指令 >
 *   	
 * @author hui.li 2017年4月26日 - 下午5:09:04
 * @Since  1.0
 */
public class GetCommentTempleteCommand {

	/**
	 * 行业
	 */
	private String industry;

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}
