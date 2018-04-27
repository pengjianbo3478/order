package com.gl365.order.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 * 利用当前日期时间+6位数字，生成请求交易流水号
 */
public class RandomUtils {
	public static String getRandomReqMsgId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String reqMsgId = now.format(formatter);
		// int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) +
		// 10000;// 获取5位随机数
		String rannum = randomStr(6);// 获取6位随机数
		return reqMsgId + rannum;// 当前时间 + 系统5随机生成位数
	}
	
	public static String randomStr(int length){
		if(length <1) return "";
		if(length >30) return "不支持生成超过30位随机数据";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
}
