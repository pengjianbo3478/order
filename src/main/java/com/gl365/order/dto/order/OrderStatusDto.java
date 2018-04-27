package com.gl365.order.dto.order;

import java.io.Serializable;

public class OrderStatusDto  implements Serializable{
	
	public OrderStatusDto( Integer orderStatus){
		this.orderStatus=orderStatus;
	}


    /**
     * 订单状态
     */
    private Integer orderStatus;

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
    
}