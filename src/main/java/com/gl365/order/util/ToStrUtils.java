package com.gl365.order.util;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
public class ToStrUtils {
	public static String toStr(Object object) {
		return ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE);
	}
}
