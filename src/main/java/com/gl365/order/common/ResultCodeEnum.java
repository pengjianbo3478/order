package com.gl365.order.common;

/**
 * api接口请求结果枚举 规则： 长度6位 |0000|00 前四位表示模块|后两位递增
 * 
 * 列如 系统类异常：0000** 用户类异常：1000**
 * 
 * @author dfs_519 2017年4月27日下午2:02:21
 */
public class ResultCodeEnum {

	public enum System {
		/**
		 * 系统保留100以下的错误码
		 */
		SUCCESS("000000", "成功"),

		PARAM_NULL("000002", "参数为空"),

		PARAM_ERROR("000003", "参数非法"),

		REQUEST_IS_NULL("000006", "错误请求"),

		SYSTEM_DATA_EXECEPTION("000008", "系统数据异常"),

		SYSTEM_TIME_OUT("000098", "请求频繁"),

		SYSTEM_ERROR("000099", "服务器错误，请稍后重试"),;

		private String code;

		private String msg;

		private System(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Merchant {
		REAL_COMMENT("400001", "改评论已提交,请勿重复评论");
		private String code;

		private String msg;

		private Merchant(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	public enum Payment {
		BEAN_Deductible_FAIL("400001", "交易系统扣豆失败"),
		BEAN_REFUND_FAIL("500001", "交易系统退豆失败");
		private String code;

		private String msg;

		private Payment(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	
	public enum Order {
		TIME_REFUND("O80000", "退款中"),
		Order_NULL("O70000", "订单不存在");
		private String code;

		private String msg;

		private Order(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
