package com.gl365.order.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.job.core.log.JobLogger;
import com.gl365.order.common.PayMentErrorCode;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.mq.wx.PayResultBo;
import com.gl365.order.dto.order.OrderStatusDto;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.CfrontService;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.GroupPayDistributeReq;
import com.gl365.order.remote.dto.OrderQueryReq;
import com.gl365.order.remote.dto.QueryOrderDetail;
import com.gl365.order.remote.dto.WxConfirmReqDTO;
import com.gl365.order.remote.dto.WxConfirmRespDTO;
import com.gl365.order.service.OrderConfirmService;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;
import com.google.gson.Gson;

@Service
public class OrderConfirmServiceImpl implements OrderConfirmService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderConfirmServiceImpl.class);

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private CfrontService cfrontService;
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;
	
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;

	public  ResultDto<?> confirm(String orderSn) {
		
		
		try {
			
			Order record=new Order();
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("orderSn",orderSn);
			Order dbOrder=orderMapper.selectByOrderSn(map);
			LOG.info("<0confirm-received>confirm dbOrder,message={}", JsonUtil.toJsonString(dbOrder));
			
			if(null==dbOrder||null==dbOrder.getOrderSn()){
				LOG.info("<1confirm-received>confirm 消息订单不存在,message={}", JsonUtil.toJsonString(map));
				return new ResultDto(ResultCodeEnum.Order.Order_NULL);
			}
			
			if(dbOrder.getOrderStatus().intValue()==OrderStatusEnum.COMPLETE_PAYMENT.getValue()){
				LOG.info("<2confirm-received>confirm 消息订单已经处理完成,message={}", JsonUtil.toJsonString(map));
				
				return  new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(dbOrder.getOrderStatus()));
			}
			
			if(dbOrder.getOrderStatus().intValue()==OrderStatusEnum.PROCESS_PAYMENT.getValue()){	
				
				
				QueryOrderDetail qod=selectRM(dbOrder.getOrderSn());
				
				if(qod==null||!String.valueOf(OrderStatusEnum.COMPLETE_PAYMENT.getValue()).equals(qod.getPayResult())){
						return  new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(dbOrder.getOrderStatus()));
				}
			
				
				//按修改行数判断
				LOG.info("3收到前端修改订单状态message={}", JsonUtil.toJsonString(record));
				int updateRow =orderMapper.updateOrderStatus(dbOrder.getOrderSn(), OrderStatusEnum.COMPLETE_PAYMENT.getValue(),dbOrder.getOrderStatus() );
				LOG.info("4 收到前端修改订单状态影响行message={}", JsonUtil.toJsonString(updateRow));
				//修改失败返回
				if(updateRow<=0){
					Order rtOrder=orderMapper.selectByOrderSn(map);
					LOG.info("5 收到修改订单状态影响行为0直接返回！！");
					return  new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(rtOrder.getOrderStatus()));
				}
				
				
				
				WxConfirmReqDTO wxc= new WxConfirmReqDTO();
	//			wxc.setBankType(payment.getBankType());//目前没有数据
	//			wxc.setPayDesc(payment.getPayDesc());//目前没有数据
				wxc.setCashAmount(dbOrder.getCashAmount());
				wxc.setDecAmount(dbOrder.getDecAmount());
	//			wxc.setDecResult(payment.getDecResult());
				wxc.setMerchantOrderNo(dbOrder.getOrderSn());
				wxc.setOrganCode("10003");
				wxc.setOrganOrderNo(dbOrder.getRmOrderNo());
				wxc.setOrganPayTime( LocalDateTime.now());
				wxc.setPayResult("0");
				wxc.setTransactionId(dbOrder.getTransactionId());
				
				record.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				record.setOrderSn(dbOrder.getOrderSn());
				
				LOG.info("<6 confirm-received>抵扣乐豆,message={}", JsonUtil.toJsonString(wxc));
				WxConfirmRespDTO wxrt = null;
				try{
					wxrt=paymentClient.confirm(wxc);
					LOG.info("<7 confirm-received>抵扣乐豆返回,message={}单号：{}", JsonUtil.toJsonString(wxrt),dbOrder.getOrderSn());
				}catch(Exception e){
					LOG.error("调用交易系统抵扣乐豆 paymentClient.confirm 异常,e:",e);
					return new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(OrderStatusEnum.PROCESS_PAYMENT.getValue()));//扣斗异常会未知状态
				}
				
				
				if(wxrt == null){
					record.setBeanType(BeanStatusEnum.bean_init.getValue());
				}else{
					//判断抵扣乐豆是否成功
					if(wxrt.getResultCode().equals(ResultCodeEnum.System.SUCCESS.getCode())){
						record.setBeanType(BeanStatusEnum.bean_success.getValue());
					}else if(PayMentErrorCode.map.containsKey(wxrt.getResultCode())){
						record.setBeanType(BeanStatusEnum.bean_fail.getValue());
					}else{
						record.setBeanType(BeanStatusEnum.bean_init.getValue());
					}
				}
				orderMapper.updateByOrderSnSelective(record);
				LOG.info("<8 confirm-received>修改订单状态完成,message={}", JsonUtil.toJsonString(record));
				
				//乐豆抵扣失败发起退款
				if(BeanStatusEnum.bean_fail.getValue() == record.getBeanType()){
					OrderRefundCommand command = new OrderRefundCommand();
					command.setOrigOrderSn(record.getOrderSn());
					command.setTotalAmount(dbOrder.getCashAmount());
					ResultDto<?> refund= refundOrderService.refundOrder(command);
					LOG.info("<8.1 confirm-received>退款单返回结果,message={}", JsonUtil.toJsonString(refund));
					 Order refundOrder=orderMapper.selectByOrderSn(map);
					 return new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(refundOrder.getOrderStatus()));
				}else{
					//发送通知到微信公众号
					Order order=orderMapper.selectByOrderSn(map);
					order.setPaymentTime(str2localDateTime(qod.getTransTime()));
					orderNotifyProducer.sendConfirm(order);
					if(dbOrder.getPaymentConfig()==PaymentConfigEnum.COLLECTIVE_MAIN.getValue()||dbOrder.getPaymentConfig()==PaymentConfigEnum.COLLECTIVE_MINOR.getValue()){
						callCfront( dbOrder);
					}
				}
			}else{
				LOG.info("9 当前订单状态有误,message={}", JsonUtil.toJsonString(map));
				 Order rtOrder=orderMapper.selectByOrderSn(map);
				return  new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(rtOrder.getOrderStatus()));
			}
		} catch (Exception e) {
			LOG.error("<10 confirm-received>订单接收网关成功消费信息异常	===>	RedenvelopeConsumer.receive exception,e:{}", e);
			return new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(OrderStatusEnum.PROCESS_PAYMENT.getValue()));//异常会未知状态
		}
		
		return new ResultDto(ResultCodeEnum.System.SUCCESS,new OrderStatusDto(OrderStatusEnum.COMPLETE_PAYMENT.getValue()));
	}
	
	
	public QueryOrderDetail selectRM(String orderSn){
		OrderQueryReq req=new OrderQueryReq();
		req.setMchOrderNo(orderSn);
		LOG.info("订单系统查询RM订单------- 单号--->{}",orderSn);
		com.gl365.order.remote.dto.ResultDto<QueryOrderDetail> result= wxPayGatewayService.doAction(req);
		LOG.info("订单系统查询RM订单------- 结果--->{}",JsonUtil.toJsonString(result));
		if(ResultCodeEnum.System.SUCCESS.getCode().equals(result.getRespCode())){
			return result.getResult();
		}else{
			return null;
		}
		
	}
	
	
	public void callCfront(Order dbOrder){
		try{
			GroupPayDistributeReq req=new GroupPayDistributeReq();
			req.setGroupId(dbOrder.getGroupId());
			req.setUserId(dbOrder.getMemberId());
			LOG.info("调用cfrontService,message={}", JsonUtil.toJsonString(req));
			ResultDto<?> rlt = cfrontService.cancel(req);
			LOG.info("调用cfrontService,rlt={}", JsonUtil.toJsonString(rlt));
		}catch(Exception e){
			LOG.error("调用cfrontService异常 ,e:{}", e);
		}
		
	}
	
	private LocalDateTime str2localDateTime(String organPayTime){
		try{
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(organPayTime,df);
			return ldt;
		}catch(Exception e){
			LOG.warn("日期类型不匹配转换错误,organPayTime={} 错误：{}", JsonUtil.toJsonString(organPayTime),e);	
			return LocalDateTime.now();
		}
		
	}
	

	private PayResultBo newInstance(String message) {
		return new Gson().fromJson(message, PayResultBo.class);
	}

}
