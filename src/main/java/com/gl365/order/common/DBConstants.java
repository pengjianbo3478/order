package com.gl365.order.common;

import java.util.HashMap;
import java.util.Map;

/**
 * < 数据字典 >
 * 
 * @author hui.li 2017年4月21日 - 下午8:48:53
 * @Since 1.0
 */
public class DBConstants {

	public static final Integer MAX_GRADE = 5, AUTO_INCREASE_1 = 1, AUTO_INCREASE_0 = 0; // 评论默认最高分，自动增长评论次数

	public static final Integer XF = 0, CX = 1;	// 商家账单状态  消费：0  撤销 ： 1

	public static final Integer UN_COMMENT = 0; // 默认用户没有评论

	public static final Integer REAL_COMMENT = 1; // 用户真实评论',

	public static final Map<String, Object> GRADE_MAP;

	static {
		GRADE_MAP = new HashMap<>();
		GRADE_MAP.put(GRADE_LEAVE.ALL.getValue(), null);
		GRADE_MAP.put(GRADE_LEAVE.GOOD.getValue(), new Integer[] { 4, 5 });
		GRADE_MAP.put(GRADE_LEAVE.MEDIUM.getValue(), new Integer[] { 3 });
		GRADE_MAP.put(GRADE_LEAVE.BAD.getValue(), new Integer[] { 1, 2 });
	}

	/**
	 * < 评分级别 >
	 * 
	 * @author hui.li 2017年4月21日 - 下午8:52:39
	 * @Since 1.0
	 */
	public enum GRADE_LEAVE {

		ALL("0", "全部评价"), GOOD("1", "好评"), MEDIUM("2", "中评"), BAD("3", "差评");

		private String value;
		private String desc;

		private GRADE_LEAVE(String value, String desc) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

	/**
	 * < 广告类型 >
	 * 
	 * @author hui.li 2017年4月21日 - 下午8:52:39
	 * @Since 1.0
	 */
	public enum AD_TYPE {

		AD("AD", 0), BANNER("BANNER", 1);

		private String name;
		private int value;

		private AD_TYPE(String name, int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	/**
	 * < App更新类型 >
	 * 
	 * @author hui.li 2017年4月21日 - 下午8:52:39
	 * @Since 1.0
	 */
	public enum APP_UPDATE_TYPE {

		MUST("强制更新", 2), WILL("允许跳过", 1);

		private String name;
		private int value;

		private APP_UPDATE_TYPE(String name, int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
}
