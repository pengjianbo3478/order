package com.gl365.order.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.build.OrderBuild;
import com.gl365.order.common.build.OrderRefundBuild;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.merchant.MerchantBasicsInfoDto;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.dto.mq.payment.model.PayReturn;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.dto.order.command.GetOrderSn4OrderCommand;
import com.gl365.order.dto.order.command.UpdateOrderCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.enums.PaymentTypeEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.mapper.OrderRefundMapper;
import com.gl365.order.model.Order;
import com.gl365.order.model.OrderRefund;
import com.gl365.order.model.OrderSequence;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.OrderInfoRsp;
import com.gl365.order.remote.dto.PrepayBoReq;
import com.gl365.order.service.MerchantService;
import com.gl365.order.service.OrderService;
import com.gl365.order.service.repo.UserServiceRepo;
import com.gl365.order.util.JsonUtil;
import com.gl365.order.util.ReflectUtils;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private UserServiceRepo userServiceRepo;
	
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
	public ResultDto<?> createOrder(CreateOrderCommand command) {
		LOG.info("创建订单  OrderServiceImpl. createOrder={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "paymentConfig", true, 2,null);
			checkStrLength(command, "memberId", true, 35,null);
			checkStrLength(command, "merchantNo", true, 35,8);
		} catch (Exception e1) {
			LOG.info("新增订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		
		if(!checkEnums(command.getPaymentConfig())){
			LOG.info("新增订单异常场景参数校验不通过：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "新增订单异常场景参数校验不通过");
		}
		
		try {
			// 构建订单信息
			Order order = OrderBuild.buildCreate(command);
			BigDecimal payLdMoney=command.getPayLdMoney();
			// 获取单号
			String orderSn =  getOrderSn(command.getPaymentConfig(),command.getMerchantNo());
			order.setOrderSn(orderSn);
//			command.setOrderSn(orderSn);
			LOG.info("创建订单生成单号  OrderServiceImpl. getOrderSn={}", orderSn);
			// 保存商户信息
			
			order=setGroupId(order);
			//扣豆初始状态
			order.setBeanType(BeanStatusEnum.bean_init.getValue());
			order.setPayChannel("rm");
			orderMapper.insertSelective(order);
			LOG.info("创建订单入库完成  OrderServiceImpl. createOrder={}", JsonUtil.toJsonString(order));
			OrderDto orderDto=OrderBuild.buildOrderDto(order);
			PrepayBoReq req=new PrepayBoReq();
			req=PrepayBoReq.getPrepayBoReq(command);
			req.setMerchantOrderNo(order.getOrderSn());
	        String callbackUrl = String.format(req.getCallbackUrl(), order.getOrderSn());
	        req.setCallbackUrl(callbackUrl);
	        req.setPayLdMoney(payLdMoney);

			LOG.info("创建订准备调用融脉  OrderServiceImpl. doAction={}", JsonUtil.toJsonString(req));
			com.gl365.order.remote.dto.ResultDto<OrderInfoRsp> result=wxPayGatewayService.doAction(req);
			LOG.info("创建订准备融脉返回  OrderServiceImpl. doAction={}", JsonUtil.toJsonString(result));

			if(ResultCodeEnum.System.SUCCESS.getCode().equals(result.getRespCode())){
				orderDto.setTokenId(result.getResult().getTokenId());
				orderDto.setPayInfo(result.getResult().getPayInfo());
				LOG.info("创建订单生成单号  OrderServiceImpl buildOrderDto={}", JsonUtil.toJsonString(orderDto));
				Order record=new Order();
				record.setOrderSn(orderSn);
				if(order.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MAIN.getValue()){
					BigDecimal beanAmount=order.getGroupMainuserPay().subtract(result.getResult().getPayCash());//乐豆支付
					record.setBeanAmount(beanAmount);
				}else{
					record.setBeanAmount(result.getResult().getPayPoints());//乐豆支付
				}
				
				record.setCashAmount(result.getResult().getPayCash());//现金支付
				record.setGiftAmount(result.getResult().getRebatePoints());//返利
				record.setQueryTime(LocalDateTime.now());
				record.setQuerySum(0);
				record.setRmOrderNo(result.getResult().getCpOrderNo());
				
				record.setTransactionId(result.getResult().getTransactionId());
				record.setCashAmount(result.getResult().getPayCash());
				record.setDecAmount(result.getResult().getDecAmount());
				
				orderMapper.updateQuerySumByOrderSn(record);
				LOG.info("更新网关数据到order  OrderServiceImpl. doAction={}", JsonUtil.toJsonString(record));
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
			}else{
				LOG.info("预下单失败=================》{}", JsonUtil.toJsonString(orderDto));
				//TODO 预下单失败改变状态
				ResultDto resultDot= new ResultDto();
				resultDot.setData(result.getResult());
				resultDot.setDescription(result.getRespMsg());
				resultDot.setResult(result.getRespCode());
				return resultDot;
			}
			
		} catch (Exception e) {
			LOG.error("新增订单异常：exception:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class }, readOnly = false)
	public ResultDto<?> updateOrder(UpdateOrderCommand command) {
		try {
			checkStrLength(command, "orderSn", true, 35,null);
		} catch (Exception e1) {
			LOG.info("修改订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		
			LOG.info("修改订单：updateOrder:{}",JsonUtil.toJsonString(command));
			// 构建订单信息
			Order order = OrderBuild.buildUpdate(command);
			try {
				if(null!=order.getMerchantNo()){
					// 查询商户信息
					ResultDto<MerchantBasicsInfoDto> resultMerchantBasics= merchantService.getBasicsDetail(order.getMerchantNo());
					LOG.info("修改订单：updateOrder:MerchantBasicsInfoDto{}",JsonUtil.toJsonString(resultMerchantBasics));
					if(null!=resultMerchantBasics&&null!=resultMerchantBasics.getData()){
						MerchantBasicsInfoDto merchantBasics=resultMerchantBasics.getData();
						if(null!=merchantBasics.getSaleRate()){
							// 总金额计算返豆
							BigDecimal giftAmount= order.getTotalAmount().multiply(merchantBasics.getSaleRate());
							BigDecimal hundred= new BigDecimal(100);
							giftAmount=giftAmount.divide(hundred);
							order.setGiftAmount(giftAmount);
						}
					}
				}
			
			} catch (Exception e) {
				LOG.error("修改订单异常：exception:{}", e);
				LOG.error("修改订单异常反豆率强制为0：order.giftAmount:{}", order);
				//return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
			}
			
			if(OrderStatusEnum.PROCESS_PAYMENT.getValue()== order.getOrderStatus().intValue()){
				order.setQuerySum(1);
				order.setQueryTime(LocalDateTime.now());
			}
			
			try {
			
			LOG.info("修改订单：updateByOrderSnSelective{}",JsonUtil.toJsonString(order));
			orderMapper.updateByOrderSnSelective(order);
			try {//群主支付关闭未支付订单
				if(order.getOrderType().intValue()==5&order.getOrderStatus().intValue()==1){
					LOG.info("群主关单：updateStatusByOGroup{}",JsonUtil.toJsonString(order));
					Map map=new HashMap();
					map.put("orderSn", order.getOrderSn());
					Order updateOrder=orderMapper.selectByOrderSn(map);
					LOG.info("群主关单 ：updateOrder{}",JsonUtil.toJsonString(updateOrder));
					orderMapper.updateStatusByOGroup(updateOrder.getGroupId());
				}
			} catch (Exception e) {
				LOG.error("修改订单异常：updateStatusByOGroup:{}", e);
			}
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("修改订单异常：exception:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}


	
	

	public ResultDto<OrderDto> getOrderBySn(GetOrderSn4OrderCommand command) {
		try {
			// 构建订单信息
			Map<String,Object> selectOrder = OrderBuild.buildGetOrderSn(command);
			// 保存商户信息
			if(0==selectOrder.size()){
				LOG.info("查询订单参数为空：createOrder:{}");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_NULL);
			}
			//退款单查询
			if(command.getOrderSn().startsWith(String.valueOf(PaymentConfigEnum.refund.getValue()))){
				Order order= new Order();
				OrderRefund orderRefund=orderRefundMapper.selectBySn(command.getOrderSn());
				if(null!=orderRefund&&null!=orderRefund.getOrderSn()){
					BeanUtils.copyProperties(orderRefund,order);
					Order origOrder=orderMapper.selectBySn(orderRefund.getOrigOrderSn());
					order.setMerchantNo(origOrder.getMerchantNo());
					order.setTerminal(origOrder.getTerminal());
					order.setNoBenefitAmount(origOrder.getNoBenefitAmount());
					LOG.info("退款订单查询 order.getOrderSn：createOrder:{}"+order.getOrderSn());
					OrderDto orderDto=OrderBuild.buildOrderDto(order);
					return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
				}
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
			}
			
			Order order= orderMapper.selectByOrderSn(selectOrder);
			if(null!=order&&null!=order.getOrderSn()){
				LOG.info("查询订单 order.getOrderSn：createOrder:{}"+order.getOrderSn());
				OrderDto orderDto=OrderBuild.buildOrderDto(order);
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
			}
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("查询订单参数为空：createOrder:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class }, readOnly = false)
	public void mqUpdateOrderDetailed(PaymentMQ mq){
//		LOG.info("网上消费类型信息  OrderServiceImpl. mqUpdateOrderDetailed.mq.getTranType={}", JsonUtil.toJsonString(mq.getTranType()));
		//网上消费修改订单信息
		if(mq.getTranType().equals(PaymentTypeEnum.WSXF.getValue())){
			LOG.info("网上消费修改订单信息  OrderServiceImpl. mqUpdateOrderDetailed={}", JsonUtil.toJsonString(mq));
			completionOrder(mq);
		//网上消费冲正
		}else if(mq.getTranType().equals(PaymentTypeEnum.WSXF_CZ.getValue())){//
			 String orderSn=mq.getPaymentBody().getPayMain().getMerchantOrderNo();
			 updateStatusByOrderSn(orderSn,OrderStatusEnum.REVERSAL_PAYMENT.getValue());
		//网上消费撤销
		}else if(mq.getTranType().equals(PaymentTypeEnum.WSXF_CX.getValue())){//
			 String orderSn=mq.getPaymentBody().getPayMain().getMerchantOrderNo();
			 updateStatusByOrderSn(orderSn,OrderStatusEnum.REVOKE_PAYMENT.getValue());
		//全额退货
		}else if(mq.getTranType().equals(PaymentTypeEnum.WSXF_TH.getValue())){
			createOrderRefund(mq.getPaymentBody().getPayReturn(),OrderStatusEnum.ALL_REFUND.getValue());
		//部分退货
		}else if(mq.getTranType().equals(PaymentTypeEnum.WSXF_BF_TH.getValue())){
			createOrderRefund(mq.getPaymentBody().getPayReturn(),OrderStatusEnum.PART_REFUND.getValue());
		//网上消费撤销冲正（修改为正常订单状态）
		}else if(mq.getTranType().equals(PaymentTypeEnum.WSXF_CX_CZ.getValue())){
			 String orderSn=mq.getPaymentBody().getPayMain().getMerchantOrderNo();
			 updateStatusByOrderSn(orderSn,OrderStatusEnum.COMPLETE_PAYMENT.getValue());
		}else{
			LOG.info("网上消费类型信息没有匹配  OrderServiceImpl. mqUpdateOrderDetailed", JsonUtil.toJsonString(mq));
		}
	}
	
	/**
	 * 
	 * @Title: createOrderRefund
	 * @Description: 创建退款订单
	 * @param payReturn
	 * @return: void
	 */
	private void createOrderRefund(PayReturn payReturn,int orderStatus){
		try{
			OrderRefund record= OrderRefundBuild.buildCreate(payReturn);
			record.setOrderStatus(orderStatus);
			orderRefundMapper.insertSelective(record);
		}catch(Exception e){
			LOG.error("createOrderRefund exception,e:"+e);
		}
	}
	
	private void updateStatusByOrderSn(String orderSn,Integer orderStatus){
		try{
			Order order=new Order();
			order.setOrderSn(orderSn);
			order.setOrderStatus(orderStatus);
			orderMapper.updateStatusByOrderSn(order);
		}catch(Exception e){
			LOG.error("updateStatusByOrderSn exception,e:{}",e);
		}
	}
	
	/**
	 * 
	 * @Title: completionOrder
	 * @Description: mq修改订单信息
	 * @param mq
	 * @return: void
	 */
	private void completionOrder(PaymentMQ mq){
		try{
			Order order = OrderBuild.buildPaymentMQ4Order(mq);
			order.setOrderStatus(1);
			int influence=orderMapper.updateByOrderSnSelective(order);
//			if(0==influence){
//				LOG.info("同步mq"+mq.getPaymentBody().getPayMain().getMerchantOrderNo());
//			}
		}catch(Exception e){
			LOG.error("completionOrder exception,e:"+e);
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
	
	
	
	public ResultDto<OrderDto> getOrderByMemberId(String command) {
		try {
			// 构建订单信息
			// 保存商户信息
			if(null==command){
				LOG.info("查询订单参数为空：getOrderByMemberId:{}");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_NULL);
			}
			
			//获取关联userId
			List<String> userIdList = getUserRelationId(command);
			if(null == userIdList){
				return new ResultDto<>(ResultCodeEnum.System.SYSTEM_DATA_EXECEPTION);
			}
			//根据关联后的ids查询
			//Order order= orderMapper.selectByOrderMemberId(command);
			Order order= orderMapper.selectByOrderMemberIdList(userIdList);
			
			if(null!=order&&null!=order.getOrderSn()){
				LOG.info("查询订单 order.getOrderSn：getOrderByMemberId:{}"+order.getOrderSn());
				OrderDto orderDto=OrderBuild.buildOrderDto(order);
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
			}
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("查询订单参数为空：getOrderByMemberId:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	//获取该userId关联的其它userId
	private List<String> getUserRelationId(String memberId){
		LOG.info("获取关联userId param={}", memberId);
		if(StringUtils.isBlank(memberId)){
			return null;
		}
		ResultDto<Set<String>> userIdSet = userServiceRepo.queryUserRelationByUserId(memberId);
		LOG.info("获取关联userId result={}", JsonUtils.toJsonString(userIdSet));
		if(!(ResultCodeEnum.System.SUCCESS.getCode().equals(userIdSet.getResult()) && (!userIdSet.getData().isEmpty()))){
			return null;
		}
		List<String> userIdList = new ArrayList<>();
		userIdList.addAll(userIdSet.getData());
		return userIdList;
	}
	
	public PageDto<OrderDto> getByOrderMainMemberId(String memberId, int pageNo, int pageSize){
		PageHelper.startPage(pageNo, pageSize);
		if(null==memberId||0==memberId.length()){
			LOG.info("查询主单参数校验不通过：exception:{}");
			return null;
		}
		
		//获取关联userId
		List<String> userIdList = getUserRelationId(memberId);
		if(null == userIdList){
			return new PageDto<>();
		}
		//根据关联后的ids查询
		//List<Order> list=orderMapper.getByOrderMainMemberId(memberId);
		List<Order> list=orderMapper.getByOrderMainMemberIdList(userIdList);
		
		if(null==list||0==list.size()){
			return new PageDto<>();
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		Long total=pageInfo.getTotal();
		
		PageDto <OrderDto> pg=	new PageDto<OrderDto>();
		pg.setCurPage(pageNo);
		pg.setList(orderDtoList);
		pg.setPageSize(pageSize);
		pg.setTotalCount((Integer)total.intValue());
		pg.setTotalPage(pageInfo.getPages());
		
		return pg;
	}
	
	public PageDto<OrderDto> getByOrderSonMemberId(String memberId, int pageNo, int pageSize){
		PageHelper.startPage(pageNo, pageSize);
		if(null==memberId||0==memberId.length()){
			LOG.info("查询主单参数校验不通过：exception:{}");
			return null;
		}
		
		// 获取关联userId
		List<String> userIdList = getUserRelationId(memberId);
		if (null == userIdList) {
			return new PageDto<>();
		}
		// 根据关联后的ids查询
		//List<Order> list=orderMapper.getByOrderSonMemberId(memberId);
		List<Order> list=orderMapper.getByOrderSonMemberIdList(userIdList);
				
		if(null==list||0==list.size()){
			return new PageDto<>();
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		
		orderDtoList.forEach(o->{
			if(null!=o.getGroupId()){
				Order mainOrder=orderMapper.getByMainOrderGroupId(o.getGroupId());
				o.setMainOrderStatus(mainOrder.getOrderStatus());
			}
		});
		
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		Long total=pageInfo.getTotal();
		
		PageDto <OrderDto> pg=	new PageDto<OrderDto>();
		pg.setCurPage(pageNo);
		pg.setList(orderDtoList);
		pg.setPageSize(pageInfo.getPageSize());
		pg.setTotalCount((Integer)total.intValue());
		pg.setTotalPage(pageInfo.getPages());
		
		return pg;
	}
	
	public PageDto<OrderDto> getByOrderGroupId(String groupId, int pageNo, int pageSize){
		PageHelper.startPage(pageNo, pageSize);
		if(null==groupId||0==groupId.length()){
			LOG.info("查询群单参数校验不通过：exception:{}");
			return null;
		}
		List<Order> list=orderMapper.getByOrderGroupId(groupId);
		if(null==list||0==list.size()){
			return new PageDto<>();
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		Long total=pageInfo.getTotal();
		
		PageDto <OrderDto> pg=	new PageDto<OrderDto>();
		pg.setCurPage(pageNo);
		pg.setList(orderDtoList);
		pg.setPageSize(pageInfo.getPages());
		pg.setTotalCount((Integer)total.intValue());
		pg.setTotalPage(pageInfo.getPageSize());
		
		return pg;
	}
	
	@Override
	public PageDto<OrderDto> getByOrderGroupIdCancelOrder(String groupId, String status, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		if(null==groupId||0==groupId.length()){
			LOG.info("查询群单参数校验不通过：exception:{}");
			return null;
		}
		
		List<Integer> orderStatus = new ArrayList<>();
		String[] statusArray = status.split(",");
		for (int i = 0; i < statusArray.length; i++) {
			String value = statusArray[i];
			if(StringUtils.isNotBlank(value)){
				orderStatus.add(Integer.valueOf(value));
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("groupId", groupId);
		map.put("orderStatus", orderStatus);
		
		List<Order> list=orderMapper.getByOrderGroupIdCancelOrder(map);
		if(null==list||0==list.size()){
			return new PageDto<>(0, pageNo, pageSize, new ArrayList<>());
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		
		//Map<String, OrderDto>  orderMap=orderDtoList.stream().collect(Collectors.toMap(OrderDto::getMemberId, Function.identity()));
		Map<String, OrderDto>  orderMap=new HashMap<String, OrderDto>();
		for (OrderDto orderDto:orderDtoList ){
			orderMap.put(orderDto.getMemberId(), orderDto);
		}
		
		   Collection<OrderDto> valueCollection = orderMap.values();
		 
		    List<OrderDto> valueList = new ArrayList<OrderDto>(valueCollection);
		
		
		
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		Long total=pageInfo.getTotal();
		
		PageDto <OrderDto> pg=	new PageDto<OrderDto>();
		pg.setCurPage(pageNo);
		//pg.setList(orderDtoList);
		pg.setList(valueList);
		pg.setPageSize(pageInfo.getPages());
		pg.setTotalCount((Integer)total.intValue());
		pg.setTotalPage(pageInfo.getPageSize());
		
		return pg;
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
	//校验
	private boolean  checkGroupId(Order order){
		//主单
		if(order.getPaymentConfig().intValue()==PaymentConfigEnum.COLLECTIVE_MINOR.getValue()){
			if(null==order.getGroupId()||0==order.getGroupId().length()){
				return true;
			}
		}
		return false;
	}
	
	

	public ResultDto<OrderDto> getOrderMainBySn(String orderSn) {
		try {
			GetOrderSn4OrderCommand command=new GetOrderSn4OrderCommand();
			command.setOrderSn(orderSn);
			// 构建订单信息
			Map<String,Object> selectOrder = OrderBuild.buildGetOrderSn(command);
			// 保存商户信息
			if(0==selectOrder.size()){
				LOG.info("查询订单参数为空：getOrderMainBySn:{}");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_NULL);
			}
			Order order= orderMapper.selectByOrderSn(selectOrder);
			if(null!=order||null!=order.getGroupId()){
				 order=orderMapper.getByMainOrderGroupId(order.getGroupId());
			}
			if(null!=order&&null!=order.getOrderSn()){
				LOG.info("查询订单 order.getOrderSn：getOrderMainBySn:{}"+order.getOrderSn());
				OrderDto orderDto=OrderBuild.buildOrderDto(order);
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, orderDto);
			}
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("查询订单参数为空：getOrderMainBySn:{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	
}
