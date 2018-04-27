package com.gl365.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.dto.order.command.RevokeOrderCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.mapper.OrderRefundMapper;
import com.gl365.order.model.Order;
import com.gl365.order.model.OrderRefund;
import com.gl365.order.model.OrderSequence;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.OrderQueryReq;
import com.gl365.order.remote.dto.RefundBoReq;
import com.gl365.order.remote.dto.RefundInfoBoReq;
import com.gl365.order.remote.dto.RefundInfoRsp;
import com.gl365.order.remote.dto.RefundRsp;
import com.gl365.order.service.PaymentService;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;
import com.gl365.order.util.ReflectUtils;
@Service
public class RefundOrderServiceImpl implements RefundOrderService{
	private static final Logger LOG = LoggerFactory.getLogger(RefundOrderServiceImpl.class);
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;

	@Override
	public ResultDto<?> refundOrder(OrderRefundCommand command) {
		
		LOG.info("创建退款订单  RefundOrderServiceImpl. refundOrder={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "origOrderSn", true, 35,10);
		} catch (Exception e1) {
			LOG.info("创建退款订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		Map<String,String> map=new HashMap<>();
		map.put("orderSn", command.getOrigOrderSn());
		Order order=orderMapper.selectByOrderSn(map);
		if(null==order||null==order.getOrderSn()){
			LOG.info("创建退款订单原定单不存在：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "创建退款订单原定单不存在");
		}
		//已经全部退款直接返回
		if(order.getOrderStatus().intValue()==OrderStatusEnum.ALL_REFUND.getValue()){
			LOG.info("全部退款直接返回：SUCCESS:{}");
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		}
		
		if(order.getOrderStatus().intValue()==OrderStatusEnum.TIME_REFUND.getValue()){
			LOG.info("退款进行中直接返回：SUCCESS:{}");
			//public ResultDto(String code, String msg, T data) 
			return new ResultDto<>("O80000", "退款中",null);
		}
		
		//付款完成才可以退款
		if(order.getOrderStatus().intValue()==OrderStatusEnum.COMPLETE_PAYMENT.getValue()){
			
			//准备更新数据库订单状态
			Order record=new Order();
			record.setOrderSn(command.getOrigOrderSn());
			record.setOrderStatus(OrderStatusEnum.TIME_REFUND.getValue());
			LOG.info("退款操作变更订单状态  RefundOrderServiceImpl. updateByOrderSnSelective={}", JsonUtil.toJsonString(record));
			orderMapper.updateByOrderSnSelective(record);
			
			
			OrderRefund orderRefund =new OrderRefund();
			BeanUtils.copyProperties(order, orderRefund);
			orderRefund.setOrderStatus(OrderStatusEnum.TIME_REFUND.getValue());
			orderRefund.setOrigOrderSn(command.getOrigOrderSn());
			String refundId=UUID.randomUUID().toString().replace("-", "");
			orderRefund.setOrderId(refundId);
			String orderSn =  getOrderSn(PaymentConfigEnum.refund.getValue(),order.getMerchantNo());
			orderRefund.setOrderSn(orderSn);
			orderRefund.setQuerySum(0);
			orderRefund.setQueryTime(LocalDateTime.now());
			orderRefund.setCreateTime(LocalDateTime.now());
			orderRefund.setBeanType(BeanStatusEnum.bean_init.getValue());
			orderRefund.setChannel(order.getChannel());
			orderRefund.setOperatorId(command.getOperatorId());
			LOG.info("退款操作新建退款订单写入退款单  RefundOrderServiceImpl. insertSelective={}", JsonUtil.toJsonString(orderRefund));
			orderRefundMapper.insertSelective(orderRefund);
		
			RefundBoReq refundReq =new RefundBoReq();
			refundReq.setMchOrderNo(command.getOrigOrderSn());
			refundReq.setMchRefundNo(orderSn);
			refundReq.setRefundAmt(order.getCashAmount());
			refundReq.setChannel(order.getChannel());
			refundReq.setTerminal(order.getTerminal());
			LOG.info("退款操作新建退款订单调用网关  RefundOrderServiceImpl. insertSelective={}", JsonUtil.toJsonString(orderRefund));
			
			
			com.gl365.order.remote.dto.ResultDto<RefundRsp> refundRsp= wxPayGatewayService.doAction(refundReq);
			
			
			LOG.info("退款操作新建退款订单调用网关完成  RefundOrderServiceImpl. RefundRsp insertSelective={}", JsonUtil.toJsonString(refundRsp));
			if(null==refundRsp||null==refundRsp.getResult()){
				LOG.info("退款操作新建退款订单调用网关完成  getResult == null ={}", JsonUtil.toJsonString(refundRsp));
				ResultDto result=new ResultDto<>();
				result.setDescription(refundRsp.getRespMsg());
				result.setResult(refundRsp.getRespCode());
				return result;
			}
			
			//直接返回为退款处理中
			if(ResultCodeEnum.System.SUCCESS.getCode().equals(refundRsp.getRespCode())){
				
				//如果是群买单主单修改子单状态
				if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()){
					this.orderMapper.updateRevokeByGroupId(order);
				}
				if("1".equals(refundRsp.getResult().getRefundResult())){//退款成功
					record.setOrderStatus(OrderStatusEnum.ALL_REFUND.getValue());
					orderMapper.updateByOrderSnSelective(record);
					LOG.info("退款操作新建退款订单调用网关完成退款成功主单  RefundOrderServiceImpl 1 getRefundResult={}", JsonUtil.toJsonString(record));
					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(orderSn);
					refundUpdate.setOrderStatus(OrderStatusEnum.ALL_REFUND.getValue());
					orderRefundMapper.updateBySnSelective(refundUpdate);
					LOG.info("退款操作新建退款订单调用网关完成退款成功子单  RefundOrderServiceImpl 1 getRefundResult={}", JsonUtil.toJsonString(refundUpdate));
					beanRefund( order, refundRsp, orderSn);//抵扣乐豆
					
					Order dbOrder=orderMapper.selectByOrderSn(map);
					orderRefund.setPaymentTime(LocalDateTime.now());
					orderNotifyProducer.sendRefund(dbOrder, orderRefund);//发送退款中消息到mq
					LOG.info("发送退款完成消息到mq完成={}", JsonUtil.toJsonString(dbOrder));
					messageServiceImpl.send(order);
					
				}else if("3".equals(refundRsp.getResult().getRefundResult())){//退款失败（可以再次发起退款）
					record.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
					orderMapper.updateByOrderSnSelective(record);
					LOG.info("退款操作新建退款订单调用网关完成退款失败主单  RefundOrderServiceImpl 3 getRefundResult={}", JsonUtil.toJsonString(record));
					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(orderSn);
					refundUpdate.setOrderStatus(OrderStatusEnum.fail_REFUND.getValue());
					orderRefundMapper.updateBySnSelective(refundUpdate);
					LOG.info("退款操作新建退款订单调用网关完成退款成功子单  RefundOrderServiceImpl 1 getRefundResult={}", JsonUtil.toJsonString(refundUpdate));
					return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR,refundRsp.getRespMsg(),refundRsp.getResult());
				}else if("2".equals(refundRsp.getResult().getRefundResult())){//退款中
					beanRefund( order, refundRsp, orderSn);//抵扣乐豆
					//退款中消息发送
					messageServiceImpl.sending(order);
					
					Order dbOrder=orderMapper.selectByOrderSn(map);
					orderNotifyProducer.sendRefundIng(dbOrder, orderRefund);
					LOG.info("发送退款中消息到mq完成={}", JsonUtil.toJsonString(dbOrder));
				}
				
				
				
				

				
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS,refundRsp.getRespMsg(),refundRsp.getResult());
			}else if("700001".equals(refundRsp.getRespCode())){
				//700001是失败要重新发起
				record.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				orderMapper.updateByOrderSnSelective(record);
				LOG.info("退款操作新建退款订单调用网关完成退款失败主单  RefundOrderServiceImpl. 700001={}", JsonUtil.toJsonString(record));
				OrderRefund refundUpdate=new OrderRefund();
				refundUpdate.setOrderSn(orderSn);
				refundUpdate.setOrderStatus(OrderStatusEnum.fail_REFUND.getValue());
				orderRefundMapper.updateBySnSelective(refundUpdate);
				LOG.info("退款操作新建退款订单调用网关完成退款失败子单  RefundOrderServiceImpl. 700001={}", JsonUtil.toJsonString(record));
				ResultDto result=new ResultDto<>();
				result.setDescription(refundRsp.getRespMsg());
				result.setResult(refundRsp.getRespCode());
				return result;
			}else{
				//其他的你当成未知然后去轮询
				OrderRefund recordRefund=new OrderRefund();
				recordRefund.setOrigOrderSn(command.getOrigOrderSn());
				recordRefund.setOrderSn(refundId);
				recordRefund.setOrderStatus(OrderStatusEnum.UNKNOWN_REFUND.getValue());
				orderRefundMapper.updateBySnSelective(recordRefund);
				LOG.info("修改退款单状态  RefundOrderServiceImpl. updateByPrimaryKeySelective={}", JsonUtil.toJsonString(refundRsp));
				
				record.setOrderStatus(OrderStatusEnum.UNKNOWN_REFUND.getValue());
				LOG.info("退款操作变更订单状态为未知  RefundOrderServiceImpl. updateByOrderSnSelective={}", JsonUtil.toJsonString(record));
				orderMapper.updateByOrderSnSelective(record);
				ResultDto result=new ResultDto<>();
				result.setDescription(refundRsp.getRespMsg());
				result.setResult(refundRsp.getRespCode());
				LOG.info("退款操作变更订单状态为未知网关返回  RefundOrderServiceImpl ?={}", JsonUtil.toJsonString(refundRsp));
				return result;
			}
			//查询
		}else{
			 ResultDto dto= new ResultDto(ResultCodeEnum.System.SYSTEM_ERROR);
			 dto.setDescription("当前订单状态不允许退款");
			return dto;
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
	
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
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
	public ResultDto<?>  updateRevokeByOrderSn(RevokeOrderCommand command){
		
		LOG.info("创建退款订单  RefundOrderServiceImpl. updateRevokeByOrderSn={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "orderSn", true, 35,10);
			checkStrLength(command, "memberId", true, 35,null);
		} catch (Exception e1) {
			LOG.info("撤销异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		
		try {
			Order record=new Order();
			BeanUtils.copyProperties(command, record);
			//修改主单状态
			this.orderMapper.updateRevokeByOrderSn(record);
			//查询
			Order order=orderMapper.selectBySn(record.getOrderSn());
			//修改所有子单状态
			this.orderMapper.updateRevokeByGroupId(order);
		} catch (Exception e1) {
			LOG.info("创建退款订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR, e1.getMessage());
		}
		
		return new ResultDto(ResultCodeEnum.System.SUCCESS);
	}
	
	
	
	
	
	@Override
	public ResultDto<?> refundOrderFft(OrderRefundCommand command) {
		
		LOG.info("创建退款订单  RefundOrderServiceImpl. refundOrder={}", JsonUtil.toJsonString(command));
		// 检查非空及最大长度
		try {
			checkStrLength(command, "origOrderSn", true, 35,10);
			checkStrLength(command, "totalAmount", true, 35,null);
		} catch (Exception e1) {
			LOG.info("创建退款订单异常参数校验不通过：exception:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		Map<String,String> map=new HashMap<>();
		map.put("orderSn", command.getOrigOrderSn());
		Order order=orderMapper.selectByOrderSn(map);
		if(null==order||null==order.getOrderSn()){
			LOG.info("创建退款订单原定单不存在：exception:{}");
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR,   "创建退款订单原定单不存在");
		}
		
		//付款完成才可以退款
		if(order.getOrderStatus().intValue()==OrderStatusEnum.COMPLETE_PAYMENT.getValue()){
			
			OrderRefund orderRefund =new OrderRefund();
			BeanUtils.copyProperties(order, orderRefund);
			orderRefund.setOrderStatus(OrderStatusEnum.fail_REFUND.getValue());
			orderRefund.setOrigOrderSn(command.getOrigOrderSn());
			String refundId=UUID.randomUUID().toString().replace("-", "");
			orderRefund.setOrderId(refundId);
			String orderSn =  getOrderSn(PaymentConfigEnum.refund.getValue(),order.getMerchantNo());
			orderRefund.setOrderSn(orderSn);
			orderRefund.setQuerySum(0);
			orderRefund.setQueryTime(LocalDateTime.now());
			orderRefund.setCreateTime(LocalDateTime.now());
			orderRefund.setBeanType(BeanStatusEnum.bean_init.getValue());
			orderRefund.setChannel(order.getChannel());
			LOG.info("退款操作新建退款订单写入退款单  RefundOrderServiceImpl. insertSelective={}", JsonUtil.toJsonString(orderRefund));
			orderRefundMapper.insertSelective(orderRefund);
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		}else{
		
			 ResultDto dto= new ResultDto(ResultCodeEnum.System.SYSTEM_ERROR);
			 dto.setDescription("当前订单状态不允许退款");
			return dto;
		}
		
	}
	
	
	
	public void beanRefund(Order order,com.gl365.order.remote.dto.ResultDto<RefundRsp> refundRsp,String orderSn){
		try{
			if(null!=refundRsp.getResult()){
				//现金成功去调用交易扣乐豆
				RefundRsp req=new RefundRsp();
				req.setMchNo(order.getMerchantNo());
				BeanUtils.copyProperties(refundRsp.getResult(), req);
				req.setTerminal(order.getTerminal());
				
				OrderRefund or=orderRefundMapper.selectBySn(orderSn);
				req.setOperator(or.getOperatorId());
				LOG.info("发起退豆  paymentService.beanRefund={}", JsonUtil.toJsonString(req));
				ResultDto<?> res = paymentService.beanRefund(req, "10003");
				OrderRefund refund =new OrderRefund();
				refund.setOrderSn(orderSn);
				if(res.getResult().equals(ResultCodeEnum.System.SUCCESS.getCode())){
					refund.setBeanType(BeanStatusEnum.bean_success.getValue());
				}else if(ResultCodeEnum.Payment.BEAN_REFUND_FAIL.getCode().equals(res.getResult())){
					refund.setBeanType(BeanStatusEnum.bean_fail.getValue());
				}else{
					refund.setBeanType(BeanStatusEnum.bean_init.getValue());
				}
				orderRefundMapper.updateBySnSelective(refund);
			}
		}catch(Exception e){
			LOG.error("发起退豆失败  paymentService.beanRefund={}", e);
		}
	}
	
	
	public int updateRefundByOrderSn(String orderSn){
		LOG.info("强制修改订单状态：updateRefundByOrderSn:{}",orderSn);

		Order order =orderMapper.selectBySn(orderSn);
		
		if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()){
			this.orderMapper.updateRevokeByGroupId(order);
		}
		
		return orderMapper.updateRefundByOrderSn(orderSn);
	}
	
	
}
