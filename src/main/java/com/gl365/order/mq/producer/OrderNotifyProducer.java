package com.gl365.order.mq.producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gl365.aliyun.ons.OnsProducer;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.mq.order.OrderConfirm;
import com.gl365.order.dto.mq.order.OrderMain;
import com.gl365.order.dto.mq.order.OrderRefund;
import com.gl365.order.model.Order;

/**
 * < 商家评论动作生产者 >
 * 
 * @since hui.li 2017年5月10日 下午6:32:56
 */
@Component
public class OrderNotifyProducer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderNotifyProducer.class);

	@Lazy
	@Resource(name = "order-return-notify-producer")
	private OnsProducer orderProducer;

	
	public void sendConfirm(Order order){
		LOG.info("<mq-sendConfirm>发送交易确认》》》入参：{}", JsonUtils.toJsonString(order));
		try {
			OrderMain orderMain=new OrderMain();
			orderMain.setTranType("confirm");
			OrderConfirm confirm=new OrderConfirm();
			//BeanUtils.copyProperties(order, confirm);
			confirm.setTotalAmount(order.getTotalAmount());
			confirm.setBeanAmount(order.getBeanAmount());
			confirm.setCashAmount(order.getCashAmount());
			confirm.setChannel(order.getChannel());
			confirm.setCreateTime(order.getCreateTime());
			confirm.setDecAmount(order.getDecAmount());
			confirm.setGiftAmount(order.getGiftAmount());
			confirm.setGroupId(order.getGroupId());
			confirm.setGroupMainuserPay(order.getGroupMainuserPay());
			confirm.setMemberId(order.getMemberId());
			confirm.setMerchantNo(order.getMerchantNo());
			confirm.setOperatorId(order.getOperatorId());
			confirm.setOrderSn(order.getOrderSn());
			confirm.setOrderStatus(order.getOrderStatus());
			confirm.setOrderTitle(order.getOrderTitle());
			confirm.setOrigOrderSn(order.getOrigOrderSn());
			confirm.setPaymentConfig(order.getPaymentConfig());
			confirm.setPaymentTime(getLocalDate2Str(order.getPaymentTime()));
			confirm.setNoBenefitAmount(order.getNoBenefitAmount());
			confirm.setTerminal(order.getTerminal());
			
			orderMain.setConfirm(confirm);
			//String message = JsonUtils.toJsonString(orderMain);
			String message =com.gl365.order.common.gson.GsonUtils.toJson(orderMain);
			LOG.info("<mq-sendConfirm>准备发送message》》》入参：{}", message);
			orderProducer.send(message);
			LOG.info("<mq-sendConfirm>发送交易确认完成》》》入参：{}", JsonUtils.toJsonString(orderMain));
		} catch (Exception e) {
			LOG.error("<mq-sendConfirm>发送交易确认》》》异常：{}", e);
		}
	}
	
	public void sendRefund(Order order,com.gl365.order.model.OrderRefund orderRefund){
		LOG.info("<mq-sendRefund>发送退款完成》》》入参：{}", JsonUtils.toJsonString(order));
		try {
			OrderMain orderMain=new OrderMain();
			orderMain.setTranType("refund");
			OrderRefund refund=new OrderRefund();
			//BeanUtils.copyProperties(order, refund);
			refund.setBeanAmount(order.getBeanAmount());
			refund.setCashAmount(order.getCashAmount());
			refund.setChannel(order.getChannel());
			refund.setMemberId(order.getMemberId());
			refund.setMerchantNo(order.getMerchantNo());
			refund.setOperatorId(order.getOperatorId());
			refund.setOrderStatus(order.getOrderStatus());
			refund.setOrderTitle(order.getOrderTitle());
			refund.setTotalAmount(order.getTotalAmount());
			refund.setGiftAmount(order.getGiftAmount());
			refund.setRefundOperatorId(orderRefund.getOperatorId());
			refund.setCreateTime(orderRefund.getCreateTime());
			refund.setOrderSn(orderRefund.getOrderSn());
			refund.setOrigOrderSn(orderRefund.getOrigOrderSn());
			refund.setPaymentTime(orderRefund.getPaymentTime());
			refund.setNoBenefitAmount(order.getNoBenefitAmount());
			refund.setTerminal(order.getTerminal());
			
			orderMain.setRefund(refund);
//			String message = JsonUtils.toJsonString(orderMain);
			String message =com.gl365.order.common.gson.GsonUtils.toJson(orderMain);
			orderProducer.send(message);
			LOG.info("<mq-sendRefund>发送交易确认完成》》》入参：{}", JsonUtils.toJsonString(orderMain));
		} catch (Exception e) {
			LOG.error("<mq-sendRefund>发送交易确认》》》异常：{}", e);
		}
	}
	
	public void sendRefundIng(Order order,com.gl365.order.model.OrderRefund orderRefund){
		LOG.info("<mq-sendRefundIng>发送退款确认》》》入参：{}", JsonUtils.toJsonString(order));
		try {
			OrderMain orderMain=new OrderMain();
			orderMain.setTranType("refundIng");
			OrderRefund refund=new OrderRefund();
			//order.setPaymentTime(null);
			//BeanUtils.copyProperties(order, refund);
			refund.setBeanAmount(order.getBeanAmount());
			refund.setCashAmount(order.getCashAmount());
			refund.setChannel(order.getChannel());
			refund.setMemberId(order.getMemberId());
			refund.setMerchantNo(order.getMerchantNo());
			refund.setOperatorId(order.getOperatorId());
			refund.setOrderStatus(order.getOrderStatus());
			refund.setOrderTitle(order.getOrderTitle());
			refund.setTotalAmount(order.getTotalAmount());
			refund.setGiftAmount(order.getGiftAmount());
			refund.setRefundOperatorId(orderRefund.getOperatorId());
			refund.setCreateTime(orderRefund.getCreateTime());
			refund.setOrderSn(orderRefund.getOrderSn());
			refund.setOrigOrderSn(orderRefund.getOrigOrderSn());
			refund.setNoBenefitAmount(order.getNoBenefitAmount());
			refund.setTerminal(order.getTerminal());
			
			orderMain.setRefund(refund);
//			String message = JsonUtils.toJsonString(orderMain);
			String message =com.gl365.order.common.gson.GsonUtils.toJson(orderMain);
			orderProducer.send(message);
			LOG.info("<mq-sendRefundIng>发送交易确认完成》》》入参：{}", JsonUtils.toJsonString(orderMain));
		} catch (Exception e) {
			LOG.error("<mq-sendRefundIng>发送交易确认》》》异常：{}", e);
		}
	}
	
	public String getLocalDate2Str(LocalDateTime time){
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try{
			//LocalDateTime time = LocalDateTime.now();
			String localTime = df.format(time);
			return localTime;
		}catch(Exception e){
			LOG.error("日期转换错误》》》异常：{}", e);
			LocalDateTime nowTime = LocalDateTime.now();
			String localTime = df.format(nowTime);
			return localTime;
		}
	}

}
