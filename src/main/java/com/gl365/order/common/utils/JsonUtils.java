package com.gl365.order.common.utils;

import com.google.gson.Gson;

/**
 * < Google公司jar包帮助对象转JsonString >
 * 
 * 输出日志方便阅览定位
 * 
 * @author hui.li 2017年4月21日 - 下午3:32:56
 * @Since 1.0
 */
public class JsonUtils {

	public static String toJsonString(Object object) {
		return new Gson().toJson(object);
	}
}
