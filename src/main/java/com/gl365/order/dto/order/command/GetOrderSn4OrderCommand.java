package com.gl365.order.dto.order.command;

import java.io.Serializable;

public class  GetOrderSn4OrderCommand  implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5698017634020718586L;

	/**
     * 订单编号
     */
    private String orderSn;

    /**
     * 付费通序列号
     */
    private String fftSequence;

    /**
     * 付费通订单号
     */
    private String fftOrderSn;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getFftSequence() {
		return fftSequence;
	}

	public void setFftSequence(String fftSequence) {
		this.fftSequence = fftSequence;
	}

	public String getFftOrderSn() {
		return fftOrderSn;
	}

	public void setFftOrderSn(String fftOrderSn) {
		this.fftOrderSn = fftOrderSn;
	}

    
}