package com.gl365.order.mapper;

import java.util.List;

import com.gl365.order.model.OrderRefund;

public interface OrderRefundMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(String orderId);
    
    OrderRefund selectBySn (String orderSn);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
    
    OrderRefund selectByOrigOrderSn(OrderRefund record);
    
    int updateBySnSelective(OrderRefund record);
    
    
	List<OrderRefund> getOrderByQuerySum(OrderRefund record);
	
	
	/**
	 *  修改查询次数
	 * @param record
	 * @return
	 */
	int updateQuerySumByOrderSn(OrderRefund record);
}