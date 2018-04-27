package com.gl365.order.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.job.core.biz.model.ReturnT;
import com.gl365.job.core.handler.IJobHandler;
import com.gl365.job.core.handler.annotation.JobHander;
import com.gl365.job.core.log.JobLogger;
import com.gl365.order.common.Properties;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.enums.BeanStatusEnum;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.mapper.OrderRefundMapper;
import com.gl365.order.model.Order;
import com.gl365.order.model.OrderRefund;
import com.gl365.order.mq.producer.OrderNotifyProducer;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.OrderQueryReq;
import com.gl365.order.remote.dto.RefundInfoBoReq;
import com.gl365.order.remote.dto.RefundInfoRsp;
import com.gl365.order.remote.dto.RefundRsp;
import com.gl365.order.service.PaymentService;
import com.gl365.order.service.impl.MessageServiceImpl;
import com.gl365.order.util.JsonUtil;

@JobHander(value = "refundOrderJobHandler")
@Service
public class RefundOrderJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("=======>JOB, getOrderRefundByQuerySum.>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>");
		getOrderRefundByQuerySum();
		JobLogger.log("=======>JOB, getOrderRefundByQuerySum.>>>>>>>>>>>>>>end>>>>>>>>>>>>>>");
		
		
		return ReturnT.SUCCESS;
	}

	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderNotifyProducer orderNotifyProducer;
	/**
	 * 退款中状态查询
	 */
	public void getOrderRefundByQuerySum(){
		try{
		JobLogger.log("轮询查询退款订单-------开始--->");
		OrderRefund record=new OrderRefund();
		record.setQuerySum(properties.getQuerySum());
		record.setQueryTime(LocalDateTime.now());
		//查询待同步的订单
		List<OrderRefund> list=orderRefundMapper.getOrderByQuerySum(record);
		JobLogger.log("轮询查询退款订单-------list--->{0}",JsonUtils.toJsonString(list));
		for(OrderRefund order:list){
			
			OrderQueryReq req=new OrderQueryReq();
			req.setMchOrderNo(order.getOrderSn());
			JobLogger.log("轮询查询退款订单------- 单号--->{0}",order.getOrderSn());

			RefundInfoBoReq refundReq =new RefundInfoBoReq();
			refundReq.setMchRefundNo(order.getOrderSn());
			refundReq.setChannel(order.getChannel());
			
			JobLogger.log("退款操作轮询退款订单调用网关  RefundOrderServiceImpl. insertSelective={0}", JsonUtil.toJsonString(refundReq));
			com.gl365.order.remote.dto.ResultDto<RefundInfoRsp> refundRsp= wxPayGatewayService.doAction(refundReq);
			
			
			JobLogger.log("轮询查询退款订单------- 结果--->{0} 退款单号{1}",JsonUtils.toJsonString(refundRsp),order.getOrderSn());
			
			if(null==refundRsp.getResult()||null==refundRsp.getResult().getRefundResult()){
				//TODO没有当前退款订单
				//修改主单状态为正常状态可继续退款
				Order recordOrder=new Order();
				recordOrder.setOrderSn(order.getOrigOrderSn());
				recordOrder.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				recordOrder.setModifyTime(LocalDateTime.now());
				JobLogger.log("退款操作变更订单状态  RefundOrderServiceImpl. null updateByOrderSnSelective={0}", JsonUtil.toJsonString(recordOrder));
				orderMapper.updateByOrderSnSelective(recordOrder);

				OrderRefund refundUpdate=new OrderRefund();
				refundUpdate.setOrderSn(order.getOrderSn());
				refundUpdate.setOrderStatus(OrderStatusEnum.NON_DATA.getValue());
				refundUpdate.setQuerySum(order.getQuerySum()+1);
				refundUpdate.setModifyTime(LocalDateTime.now());
				JobLogger.log("修改退款单订单状态  RefundOrderServiceImpl. null updateBySnSelective={0}", JsonUtil.toJsonString(refundUpdate));
				orderRefundMapper.updateBySnSelective(refundUpdate);
				continue;
			}
			
			
			if(ResultCodeEnum.System.SUCCESS.getCode().equals(refundRsp.getRespCode())){
				
				if(refundRsp.getResult().getRefundResult().equals("0")){//0--未退款
					//修改主单状态为正常状态可继续退款
					Order recordOrder=new Order();
					recordOrder.setOrderSn(order.getOrigOrderSn());
					recordOrder.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
					JobLogger.log("退款操作变更订单状态  RefundOrderServiceImpl. query 0 updateByOrderSnSelective={0}", JsonUtil.toJsonString(recordOrder));
					orderMapper.updateByOrderSnSelective(recordOrder);

					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(order.getOrderSn());
					refundUpdate.setOrderStatus(OrderStatusEnum.NON_DATA.getValue());
					refundUpdate.setQuerySum(order.getQuerySum()+1);
					refundUpdate.setModifyTime(LocalDateTime.now());
					JobLogger.log("修改退款单订单状态  RefundOrderServiceImpl. query 0 updateBySnSelective={0}", JsonUtil.toJsonString(refundUpdate));
					orderRefundMapper.updateBySnSelective(refundUpdate);
				}else if(refundRsp.getResult().getRefundResult().equals("1")){//1--退款成功;
					
					Order recordOrder=new Order();
					recordOrder.setOrderSn(order.getOrigOrderSn());
					recordOrder.setOrderStatus(OrderStatusEnum.ALL_REFUND.getValue());
					JobLogger.log("退款操作变更订单状态  RefundOrderServiceImpl. query 1 updateByOrderSnSelective={0}", JsonUtil.toJsonString(recordOrder));
					orderMapper.updateByOrderSnSelective(recordOrder);

					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(order.getOrderSn());
					refundUpdate.setOrderStatus(OrderStatusEnum.ALL_REFUND.getValue());
					JobLogger.log("修改退款单订单状态  RefundOrderServiceImpl. query 1 updateBySnSelective={0}", JsonUtil.toJsonString(refundUpdate));
					
					if(BeanStatusEnum.bean_init.getValue() == order.getBeanType()){
						OrderRefund or=orderRefundMapper.selectBySn(order.getOrderSn());
						RefundRsp rr = new RefundRsp();
						BeanUtils.copyProperties(refundRsp.getResult(), rr);
						rr.setTerminal(order.getTerminal());
						rr.setOperator(or.getOperatorId());
						ResultDto<?> res = paymentService.beanRefund(rr, "10003");
						if(res.getResult().equals(ResultCodeEnum.System.SUCCESS.getCode())){
							refundUpdate.setBeanType(BeanStatusEnum.bean_success.getValue());
						}else if(ResultCodeEnum.Payment.BEAN_REFUND_FAIL.getCode().equals(res.getResult())){
							refundUpdate.setBeanType(BeanStatusEnum.bean_fail.getValue());
						}else{
							refundUpdate.setBeanType(BeanStatusEnum.bean_init.getValue());
						}
					}
					refundUpdate.setQuerySum(order.getQuerySum()+1);
					refundUpdate.setModifyTime(LocalDateTime.now());
					orderRefundMapper.updateBySnSelective(refundUpdate);
					JobLogger.log("乐豆抵扣修改完成={0}", JsonUtil.toJsonString(refundUpdate));
					Order orderMain =orderMapper.selectBySn(order.getOrigOrderSn());
					OrderRefund recordSearch=new OrderRefund();
					recordSearch.setOrderSn(order.getOrderSn());
					recordSearch.setOrderStatus(OrderStatusEnum.ALL_REFUND.getValue());
					JobLogger.log("查询退款单 开始selectByOrigOrderSn={0}", JsonUtil.toJsonString(recordSearch));
					OrderRefund or=orderRefundMapper.selectByOrigOrderSn(recordSearch);
					JobLogger.log("查询退款单完成selectByOrigOrderSn={0}", JsonUtil.toJsonString(or));
					//退款成功发送通知
					//messageServiceImpl.send(orderMain);
					orderMain.setPaymentTime(str2localDateTime(refundRsp.getResult().getRefundTime()));
					JobLogger.log("RM 日期转换完成={0}", JsonUtil.toJsonString(orderMain.getPaymentTime()));
					or.setPaymentTime(str2localDateTime(refundRsp.getResult().getRefundTime()));
					JobLogger.log("发送退款完成消息到mq准备={0}", JsonUtil.toJsonString(orderMain));
					orderNotifyProducer.sendRefund(orderMain, or);//发送退款中消息到mq
					JobLogger.log("发送退款完成消息到mq完成={0}", JsonUtil.toJsonString(orderMain));
					
				}else if(refundRsp.getResult().getRefundResult().equals("2")){//2--退款处理中
					
					Order recordOrder=new Order();
					recordOrder.setOrderSn(order.getOrigOrderSn());
					recordOrder.setOrderStatus(OrderStatusEnum.TIME_REFUND.getValue());
					JobLogger.log("退款操作变更订单状态  RefundOrderServiceImpl. query 2 updateByOrderSnSelective={0}", JsonUtil.toJsonString(recordOrder));
					orderMapper.updateByOrderSnSelective(recordOrder);

					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(order.getOrderSn());
					refundUpdate.setOrderStatus(OrderStatusEnum.TIME_REFUND.getValue());
					refundUpdate.setQuerySum(order.getQuerySum()+1);
					JobLogger.log("修改退款单订单状态  RefundOrderServiceImpl. query 2 updateBySnSelective={0}", JsonUtil.toJsonString(refundUpdate));
					refundUpdate.setModifyTime(LocalDateTime.now());
					orderRefundMapper.updateBySnSelective(refundUpdate);
				}else if(refundRsp.getResult().getRefundResult().equals("3")){//3--退款失败
					
					Order recordOrder=new Order();
					recordOrder.setOrderSn(order.getOrigOrderSn());
					recordOrder.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
					JobLogger.log("退款操作变更订单状态  RefundOrderServiceImpl. query 3 updateByOrderSnSelective={0}", JsonUtil.toJsonString(recordOrder));
					orderMapper.updateByOrderSnSelective(recordOrder);

					OrderRefund refundUpdate=new OrderRefund();
					refundUpdate.setOrderSn(order.getOrderSn());
					refundUpdate.setOrderStatus(OrderStatusEnum.fail_REFUND.getValue());
					refundUpdate.setQuerySum(order.getQuerySum()+1);
					JobLogger.log("修改退款单订单状态  RefundOrderServiceImpl. query 3 updateBySnSelective={0}", JsonUtil.toJsonString(refundUpdate));
					orderRefundMapper.updateBySnSelective(refundUpdate);
				}
				
				
			}else if(refundRsp.getRespCode().equals("700001")){
				Order recordOrder =new Order();
				recordOrder.setOrigOrderSn(order.getOrigOrderSn());
				recordOrder.setOrderStatus(OrderStatusEnum.COMPLETE_PAYMENT.getValue());
				orderMapper.updateByOrderSnSelective(recordOrder);
				JobLogger.log("退款操作新建退款订单调用网关完成退款失败主单  RefundOrderServiceImpl. query 700001={0}", JsonUtil.toJsonString(recordOrder));
				OrderRefund refundUpdate=new OrderRefund();
				refundUpdate.setOrderSn(order.getOrderSn());
				refundUpdate.setOrderStatus(OrderStatusEnum.fail_REFUND.getValue());
				refundUpdate.setQuerySum(order.getQuerySum()+1);
				orderRefundMapper.updateBySnSelective(refundUpdate);
				JobLogger.log("退款操作新建退款订单调用网关完成退款失败子单  RefundOrderServiceImpl. query 700001={0}", JsonUtil.toJsonString(record));
			}
			
			
		}
		}catch(Exception e) {
			JobLogger.log("退款操作异常={0}", e);
		}
	}
	
	private LocalDateTime str2localDateTime(String organPayTime){
		try{
			if(organPayTime.contains("-")){
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime ldt = LocalDateTime.parse(organPayTime,df);
				return ldt;
			}else{
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
				LocalDateTime ldt = LocalDateTime.parse(organPayTime,df);
				return ldt;
			}
	
		}catch(Exception e){
			JobLogger.log("日期类型不匹配转换错误,organPayTime={} 错误：{}", JsonUtil.toJsonString(organPayTime),e);	
			return LocalDateTime.now();
		}
		
	}
}  