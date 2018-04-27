package com.gl365.order.model.comment;

import java.io.Serializable;

public class CommentLabels implements Serializable {

	private static final long serialVersionUID = -662000402348728672L;

	private String commentId;

	private Integer commentLabelId;

	private String commentLabelName;

	public String getCommentLabelName() {
		return commentLabelName;
	}

	public void setCommentLabelName(String commentLabelName) {
		this.commentLabelName = commentLabelName;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Integer getCommentLabelId() {
		return commentLabelId;
	}

	public void setCommentLabelId(Integer commentLabelId) {
		this.commentLabelId = commentLabelId;
	}

}