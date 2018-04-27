package com.gl365.order.mapper;

import com.gl365.order.model.OrderCash;

public interface OrderCashMapper {
    int deleteByPrimaryKey(Long cashId);

    int insert(OrderCash record);

    int insertSelective(OrderCash record);

    OrderCash selectByPrimaryKey(Long cashId);

    int updateByPrimaryKeySelective(OrderCash record);

    int updateByPrimaryKey(OrderCash record);
    /**
     * 按申请单号修改状态
     * @param record
     * @return
     */
    int updateByApplyNo(OrderCash record);
}