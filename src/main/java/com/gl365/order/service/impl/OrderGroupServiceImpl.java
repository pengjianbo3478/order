package com.gl365.order.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.build.OrderBuild;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.command.CreateGroupOrderCommand;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.model.OrderSequence;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.OrderInfoRsp;
import com.gl365.order.remote.dto.PrepayBoReq;
import com.gl365.order.service.OrderGroupService;
import com.gl365.order.util.JsonUtil;
import com.gl365.order.util.ReflectUtils;

@Service
public class OrderGroupServiceImpl implements OrderGroupService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderGroupServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;


	/**
	 * 
	 * @Title: createOrder
	 * @Description: TODO
	 * @param order
	 * @return
	 * @see com.gl365.order.service.OrderService#createOrder(com.gl365.order.dto.order.command.CreateOrderCommand)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class }, readOnly = false)
	public ResultDto<?> createGroupOrder(CreateGroupOrderCommand command)  {
		LOG.info("创建订单  OrderServiceImpl. createGroupOrder={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "paymentConfig", true, 2,null);
			checkStrLength(command, "memberId", true, 35,null);
			checkStrLength(command, "merchantNo", true, 35,8);
		} catch (Exception e1) {
			LOG.info("新增订单异常参数校验不通过createGroupOrder：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		
		if(!checkEnums(command.getPaymentConfig())){
			LOG.info("新增订单异常场景参数校验不通过createGroupOrder：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "新增订单异常场景参数校验不通过");
		}
		
		try {
			// 构建订单信息
			Order order = OrderBuild.buildCreate(command);
			// 获取单号
			String orderSn =  getOrderSn(command.getPaymentConfig(),command.getMerchantNo());
			order.setOrderSn(orderSn);
			LOG.info("创建订单生成单号createGroupOrder  OrderServiceImpl. getOrderSn={}", orderSn);
			// 保存商户信息
			order=setGroupId(order);
			orderMapper.insertSelective(order);
			LOG.info("创建订单入库完成  OrderServiceImpl. createGroupOrder={}", JsonUtil.toJsonString(order));
			OrderDto orderDto=OrderBuild.buildOrderDto(order);
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
		} catch (Exception e) {
			LOG.error("新增订单异常：exception:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	
	
	/**
	 * 
	 * @Title: createOrder
	 * @Description: TODO
	 * @param order
	 * @return
	 * @see com.gl365.order.service.OrderService#createOrder(com.gl365.order.dto.order.command.CreateOrderCommand)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class }, readOnly = false)
	public ResultDto<?> createPayGroupOrder(CreateGroupOrderCommand command)  {
		LOG.info("修改群订单  OrderServiceImpl. createOrder={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "paymentConfig", true, 2,null);
			checkStrLength(command, "orderSn", true, 35,null);
		} catch (Exception e1) {
			LOG.info("修改群订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		
		if(!checkEnums(command.getPaymentConfig())){
			LOG.info("新增订单异常场景参数校验不通过：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "修改群订单异常场景参数校验不通过");
		}
		//校验是否预先创建订单
		Map<String,String> map=new HashMap<String,String>();
		map.put("orderSn",command.getOrderSn());
		Order orderDb= orderMapper.selectByOrderSn(map);
		
		if(null==orderDb||null==orderDb.getGroupId()){
			LOG.info("修改群订单异常场景参数校验不通过：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "没有查到群订单不能支付");
		}
		
		if(!checkEnums(command.getPaymentConfig())){
			LOG.info("修改群订单异常场景参数校验不通过：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "修改群订单异常场景参数校验不通过");
		}
		
		try {
			//主单子单一致
			String groupMerchantNo=command.getGroupMerchantNo();
			String openId=command.getOpenId();
			String mchCreateIp=command.getMchCreateIp();
			String operatorId=command.getOperatorId();
			//数据库补全数据 //
			CreateGroupOrderCommand commandRm=CreateGroupOrderCommand.buildCreate(orderDb,command.getCallbackUrl(),command.getGroupMainuserPay(),command.getOrderTitle());
			
			// 构建订单信息
			Order order = OrderBuild.buildCreate(commandRm);
			
			// 保存商户信息
			order=setGroupId(order);
			orderMapper.updateByOrderSnSelective(order);
			LOG.info("修改群订单入库完成  OrderServiceImpl. createOrder={}", JsonUtil.toJsonString(order));
			OrderDto orderDto=OrderBuild.buildOrderDto(order);
			
			PrepayBoReq req=new PrepayBoReq();
			req=PrepayBoReq.getPrepayBoReq(commandRm);
			req.setMerchantOrderNo(order.getOrderSn());
	        String callbackUrl = String.format(req.getCallbackUrl(), order.getOrderSn(),order.getGroupId());
	        req.setCallbackUrl(callbackUrl);
	        req.setGroupMerchantNo(groupMerchantNo);
	        req.setOpenId(openId);
	        req.setMchCreateIp(mchCreateIp);
	        req.setOperator(operatorId);
			LOG.info("创建订准备调用融脉  OrderServiceImpl. doAction={}", JsonUtil.toJsonString(req));
			com.gl365.order.remote.dto.ResultDto<OrderInfoRsp> result=wxPayGatewayService.doAction(req);
			LOG.info("创建订准备融脉返回  OrderServiceImpl. doAction={}", JsonUtil.toJsonString(result));
			
			if(ResultCodeEnum.System.SUCCESS.getCode().equals(result.getRespCode())){
				orderDto.setTokenId(result.getResult().getTokenId());
				orderDto.setPayInfo(result.getResult().getPayInfo());
				LOG.info("群买单订单生成单号 token  OrderServiceImpl buildOrderDto={}", JsonUtil.toJsonString(orderDto));
				Order record=new Order();
				record.setOrderSn(order.getOrderSn());
				//record.setBeanAmount(result.getResult().getPayPoints());
				if(order.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MAIN.getValue()){
					BigDecimal beanAmount=order.getGroupMainuserPay().subtract(result.getResult().getPayCash());//乐豆支付
					record.setBeanAmount(beanAmount);
				}else{
					record.setBeanAmount(result.getResult().getPayPoints());//乐豆支付
				}
				
				record.setCashAmount(result.getResult().getPayCash());
				record.setGiftAmount(result.getResult().getRebatePoints());
				record.setQueryTime(LocalDateTime.now());
				record.setQuerySum(0);
				record.setRmOrderNo(result.getResult().getCpOrderNo());
				
				record.setTransactionId(result.getResult().getTransactionId());
				record.setCashAmount(result.getResult().getPayCash());
				record.setDecAmount(result.getResult().getDecAmount());
				orderMapper.updateQuerySumByOrderSn(record);
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
			}else{
				LOG.info("群买单预下单失败=================》{}", JsonUtil.toJsonString(orderDto));
				//TODO 预下单失败改变状态
				ResultDto resultDot= new ResultDto();
				resultDot.setData(result.getResult());
				resultDot.setDescription(result.getRespMsg());
				resultDot.setResult(result.getRespCode());
				return resultDot;
			}
		} catch (Exception e) {
			LOG.error("修改群订单异常：exception:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 检查对象字符属性不为空且未超长
	 * 
	 * @param obj
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param mustNeed
	 *            是否必须 true必须 false非必须
	 * @param maxLength
	 *            最大字符长度
	 * @return 返回null表示检查通过，否则返回检查失败的明细描述
	 * @throws Exception
	 */
	private void checkStrLength(Object obj, String fieldName, boolean mustNeed, int maxLength,Integer minLength) throws Exception {
		Object val = ReflectUtils.getValueByFieldName(obj, fieldName);
		if (val == null && mustNeed) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		
		if (val == null && !mustNeed) {
			return;
		}
		
		String str = String.valueOf(val);
		if (!str.trim().equals(str)) {
			str = str.trim();
			ReflectUtils.setValueByFieldName(obj, fieldName, str);
		}
		if (mustNeed && str.length() == 0) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		if (mustNeed && str.length() > maxLength) {
			throw new Exception("参数" + fieldName + "值不允许大于" + maxLength + "个字符");
		}
		if(null!=minLength){
			if (mustNeed && str.length() < minLength.intValue()) {
				throw new Exception("参数" + fieldName + "值不允许小于" + minLength + "个字符");
			}
		}
	}
	

	/**
	 * 
	 * @Title: checkEnums
	 * @Description: 验证场景类型
	 * @param paymentConfig
	 * @return
	 * @return: boolean
	 */
	public boolean checkEnums(int paymentConfig){
		  
		for (PaymentConfigEnum e : PaymentConfigEnum.values()) {
	        if(e.getValue()==paymentConfig){
	            return true;
	        }
	    }
		   return false;
	}
	
	/**
	 * 订单号 XX_XX_XX_XX_XX_XX_XXXXXXX_XXXX
	 * 订单号 场景码+年年+随机+月月+随机+日日+商户流水号7位+时时+随机+分分+秒秒
	 * 生成32位订单号，yyyy+随机数前2位+MM+随机数后3位+dd+15位merchantNo+hh+ss
	 * 00：直接支付（快捷支付）01：B扫C支付02：C扫B支付03：POS
	 * @param merchantNo
	 * @return
	 */
	public String getOrderSn(int paymentConfig,String merchantNo){
		try{
		OrderSequence sequence =new  OrderSequence();
		orderMapper.insertOrderSequence(sequence);
		String seq =  String.valueOf(sequence.getId());
		String date = LocalDate.now().format(dateFormatter);
		String time = LocalTime.now().format(timeFormatter);
		//场景码
		StringBuffer sb = new StringBuffer();
		sb.append(paymentConfig);
		//年两位
		sb.append(date.substring(2, 4));
		//00六位随机数前两位
		sb.append(seq.substring(seq.length()-2, seq.length()));
		//MM月
		sb.append(date.substring(4, 6));
		//001随机数后2-4位
		sb.append(seq.substring(seq.length()-4, seq.length()-2));
		//mm日
		sb.append(date.substring(6, 8));
		//截取商户号后7位
		sb.append(merchantNo.substring(merchantNo.length()-7,merchantNo.length()));
		//hh时
		sb.append(time.substring(0, 2));
		//001随机数后2-4位
		sb.append(seq.substring(seq.length()-6, seq.length()-4));
		//mm分
		sb.append(time.substring(2, 4));
		//ss秒
		sb.append(time.substring(4, 6));
		return sb.toString();
		}catch(Exception e){
			LOG.error("getOrderSn exception,e:"+e);
			return null;
		}
	}
	
	private Order setGroupId(Order order){
		//主单
		if(order.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MAIN.getValue()){
			if(null==order.getGroupId()||0==order.getGroupId().length()){
				order.setGroupId(order.getOrderSn());
			}
		}
		return order;
	}
}
