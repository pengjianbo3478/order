package com.gl365.order.timer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gl365.job.core.biz.model.ReturnT;
import com.gl365.job.core.handler.IJobHandler;
import com.gl365.job.core.handler.annotation.JobHander;
import com.gl365.job.core.log.JobLogger;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.enums.OrderStatusEnum;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.service.RefundOrderService;

@JobHander(value = "timeOutOrderJobHandler")
@Service
public class TimeOutOrderJobHandler extends IJobHandler {


	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("=======>JOB, TimeOutOrderJobHandler.>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>");
		 updateOrderStatus();
		JobLogger.log("=======>JOB, TimeOutOrderJobHandler.>>>>>>>>>>>>>>end>>>>>>>>>>>>>>");
		
		
		return ReturnT.SUCCESS;
	}
	
	
	@Autowired
	private OrderMapper orderMapper;
/**
 * 修改过期订单状态
 */	// 暂时5分钟测试
//	@Scheduled(fixedRate = 1000 * 60 * 2)
//	@Scheduled(cron = "0 0 3 * * ?")//每天凌晨3点执行
	public void updateOrderStatus(){
		
		List<Order> list=orderMapper.selectTimeOut();
		if(null==list||0==list.size()){
			return ;
		}
		for(Order order: list){
			JobLogger.log("轮询查询超时订单-------order--->{0}",JsonUtils.toJsonString(order));
			order.setOrderStatus(OrderStatusEnum.TIME_OUT_INVALID.getValue());
			orderMapper.updateByPrimaryKeySelective(order);
			try{
				//群买单特殊处理
				if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()){
					//修改所有子单状态
					if(!getOrderMain(order.getGroupId())){//主单支付成功跳过.
						int i=this.orderMapper.updateRevokeByGroupId(order);
						JobLogger.log("轮询查询超时订单-------群买单--修改子单完成->{0} 影响行{1}",JsonUtils.toJsonString(order.getOrderSn()),i);
					}
					
				}
			}catch(Exception e){
				JobLogger.log("轮询查询超时订单-------群买单--异常->{0}",e);
			}
			
		}
		
//		orderMapper.batchUpdateStatus(list);
	}
	
	/**
	 * 群买单主单如果有支付的就跳过退款。
	 * @param groupId 群id
	 * @return
	 */
	public boolean getOrderMain(String groupId){
		boolean orderStatus=false;
		
		List<Order> list=orderMapper.getByOrderListGroupId(groupId);
		
		if(null==list&&0==list.size()){
			return orderStatus;
		}
		
		for(Order order:list){
			if(PaymentConfigEnum.COLLECTIVE_MAIN.getValue()==order.getPaymentConfig().intValue()
					&&OrderStatusEnum.COMPLETE_PAYMENT.getValue()==order.getOrderStatus().intValue()){
				orderStatus=true;
				JobLogger.log("轮询查询超时订单-------群买单--有支付成功->{0} 单号：{1}",orderStatus,order.getOrderSn());
			}
		}
		
		return orderStatus;
	}
	

}  