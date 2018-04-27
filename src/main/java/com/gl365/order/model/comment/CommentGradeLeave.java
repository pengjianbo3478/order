package com.gl365.order.model.comment;

import java.io.Serializable;

public class CommentGradeLeave implements Serializable {

	private static final long serialVersionUID = 5989186397513541325L;

	private Integer good;

	private Integer medium;

	private Integer bad;

	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public Integer getMedium() {
		return medium;
	}

	public void setMedium(Integer medium) {
		this.medium = medium;
	}

	public Integer getBad() {
		return bad;
	}

	public void setBad(Integer bad) {
		this.bad = bad;
	}

}
