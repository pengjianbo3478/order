package com.gl365.order.common.enums;

/**
 * < 打赏记录状态枚举 >
 * 
 * @since hui.li 2017年6月2日 上午10:37:08
 */
public enum CommentPersonalStatusEnum {

	NONE(0, "默认用户还没有打赏"), ING(1, "正在打赏中"), DONE(2, "用户已打赏（评论）");

	private Integer value;

	private String desc;

	private CommentPersonalStatusEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
