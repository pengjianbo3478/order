package com.gl365.order.util;

import java.math.BigDecimal;

public class MathUtil {
	
	private static BigDecimal unit = new BigDecimal(100);
	
	/**
	 *分转元
	 *@param BigDecimal分
	 *@author:summer
	 *@date:2017年6月14日 下午4:58:22
	 */
	public static BigDecimal fen2yuan(BigDecimal fen){
		return fen.divide(unit).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 *元转分
	 *@param BigDecimal元
	 *@author:summer
	 *@date:2017年6月14日 下午4:58:25
	 */
	public static BigDecimal yuan2fen(BigDecimal yuan){
		return yuan.multiply(unit).setScale(0, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 *分转元
	 *@param int
	 *@author:summer
	 *@date:2017年6月14日 下午5:03:44
	 */
	public static double fen2yuan(int fen){
		return fen2yuan(new BigDecimal(fen)).doubleValue();
	}
	
	/**
	 *分转元
	 *@param long
	 *@author:summer
	 *@date:2017年6月14日 下午5:03:44
	 */
	public static double fen2yuan(long fen){
		return fen2yuan(new BigDecimal(fen)).doubleValue();
	}
	
	/**
	 *元转分
	 *@param double
	 *@author:summer
	 *@date:2017年6月14日 下午5:03:48
	 */
	public static int yuan2fen(double yuan){
		return yuan2fen(new BigDecimal(yuan)).intValue();
	}
	
	
	
	/**
	 *分转元
	 *@param String
	 *@author:summer
	 *@date:2017年6月14日 下午5:03:51
	 */
	public static String fen2yuan(String fen){
		return fen2yuan(new BigDecimal(fen)).toString();
	}
	
	/**
	 *元转分 
	 *@param String
	 *@author:summer
	 *@date:2017年6月14日 下午5:03:54
	 */
	public static String yuan2fen(String yuan){
		return yuan2fen(new BigDecimal(yuan)).toString();
	}
	
}
