package com.gl365.order.timer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.job.core.biz.model.ReturnT;
import com.gl365.job.core.handler.IJobHandler;
import com.gl365.job.core.handler.annotation.JobHander;
import com.gl365.job.core.log.JobLogger;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.service.RefundOrderService;

@JobHander(value = "groupRefundOrderJobHandler")
@Service
public class GroupRefundOrderJobHandler extends IJobHandler {


	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("=======>JOB, getOrderRefundByQuerySum.>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>");
		groupRefund();
		JobLogger.log("=======>JOB, getOrderRefundByQuerySum.>>>>>>>>>>>>>>end>>>>>>>>>>>>>>");
		
		
		return ReturnT.SUCCESS;
	}
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private RefundOrderService refundOrderService;

	//@Scheduled(fixedRate = 1000 * 60 * 3) //3分钟一次
	public void groupRefund(){
		List<Order> list =orderMapper.selectGroupRevok();
		
		for(Order order:list){
			if(StringUtils.isNotBlank(order.getChannel())){
				OrderRefundCommand command=order4Refund( order);
				ResultDto<?> result=refundOrderService.refundOrder(command);
				JobLogger.log("轮询退款订单---rm----result--->{0}",JsonUtils.toJsonString(result));
//			}else if("fft".equals(order.getPayChannel())){
//				OrderRefundCommand command=order4Refund( order);
//				ResultDto<?> result=refundOrderService.refundOrderFft(command);
//				JobLogger.log("轮询退款订单---fft----result--->{0}",JsonUtils.toJsonString(result));
			}
			
		}
	}
	
	private OrderRefundCommand order4Refund(Order order){
		OrderRefundCommand command=new OrderRefundCommand();
		command.setBeanAmount(order.getBeanAmount());
		command.setCashAmount(order.getCashAmount());
		command.setChannel(order.getChannel());
		command.setMemberId(order.getMemberId());
		command.setMerchantNo(order.getMerchantNo());
		command.setOperatorId(order.getOperatorId());
		command.setOrderSn(order.getOrderSn());
		command.setOrderStatus(order.getOrderStatus());
		command.setOrderTitle(order.getOrderTitle());
		command.setOrderType(order.getOrderType());
		command.setOrigOrderSn(order.getOrderSn());
		command.setTerminal(order.getTerminal());
		command.setTotalAmount(order.getTotalAmount());
		return command;
	}

}  