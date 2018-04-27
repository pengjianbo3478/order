package com.gl365.order.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	public static LocalDateTime parseDate(String dateStr){
		LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
		return date;
	}
	
	public static String format(LocalDateTime date){
		return date.format(formatter);
	}
}
