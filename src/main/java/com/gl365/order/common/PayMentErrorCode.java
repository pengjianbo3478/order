package com.gl365.order.common;

import java.util.HashMap;
import java.util.Map;


public class PayMentErrorCode {
	
	public static Map<String,String> map=new HashMap<String,String>();
	static{
		map.put("8001",	"用户状态不正常");
		map.put("3000",	"用户不存在");
		map.put("3003",	"会员名称为空");
		map.put("3004",	"发展会员机构ID为空");
		map.put("3005",	"发展会员机构为空");
		map.put("3007",	"会员手机号为空");
		map.put("3009",	"用户ID为空");
	
		map.put("8002",	"商户状态不正常");
		map.put("2000",	"商户不存在");
		map.put("2001",	"返佣方式非空");
		map.put("2002",	"返佣率非空");
		map.put("2003",	"返利率非空且值不能大于99");
		map.put("2004",	"商家所在区域非空");
		map.put("2005",	"商家所在省非空");
		map.put("2006",	"商家所在市非空");
		map.put("2007",	"给乐商户号非空");
		map.put("2008",	"商家简称非空");
		map.put("2009",	"发展机构非空");
		map.put("2010",	"清算支付公司非空");
		map.put("2011",	"发展商户机构上级机构非空");
		map.put("2022",	"推广业务员非空");
		map.put("2023",	"POS借记卡手续费费率非空且大于0");
		map.put("2024",	"POS借记卡手续费封顶值非空");
		map.put("2025",	"POS贷记卡手续费费率非空且大于0");
		map.put("2026",	"POS贷记卡手续费封顶值非空");
		map.put("2027",	"在线支付借记卡手续费率非空且大于0");
		map.put("2028",	"在线支付借记卡手续费封顶值非空");
		map.put("2029",	"在线支付贷记卡手续费率非空且大于0");
		map.put("2030",	"在线支付贷记卡手续费封顶值非空");
		map.put("2031",	"商户乐豆支付开关非空");
		map.put("2032",	"商家所在区域代理商非空");
		map.put("2034",	"商户交易通道[线上或线下支付]非空");
	
		map.put("A00001", 	"非空参数校验失败");
		map.put("A10001", 	"借贷标识dcType参数错误");
		map.put("A10002", 	"支付渠道agentId参数错误");
		map.put("A10007", 	"操作金额与返利金额不允许同时为0");
		map.put("A00001", 	"非空参数校验失败");
		map.put("A10004", 	"账户余额修改失败:未查询到账户！");
		map.put("A10018", 	"账户已失效");
		map.put("A10003", 	"余额不足");
		map.put("A10019", 	"流水号重复 ");
	}
}
