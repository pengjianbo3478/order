package com.gl365.order.dto.order;

import java.math.BigDecimal;

public class OrderListSum{

    /**
     * 订单总金额
     */
    private BigDecimal totalAmountSum;

    /**
     * 乐豆金额
     */
    private BigDecimal beanAmountSum;

    /**
     * 现金金额
     */
    private BigDecimal cashAmountSum;

    /**
     * 返利金额(返乐豆)
     */
    private BigDecimal giftAmountSum;
    
    /**
     * 不可返利金额
     */
    private BigDecimal noBenefitAmountSum;

	public BigDecimal getTotalAmountSum() {
		return totalAmountSum;
	}

	public void setTotalAmountSum(BigDecimal totalAmountSum) {
		this.totalAmountSum = totalAmountSum;
	}

	public BigDecimal getBeanAmountSum() {
		return beanAmountSum;
	}

	public void setBeanAmountSum(BigDecimal beanAmountSum) {
		this.beanAmountSum = beanAmountSum;
	}

	public BigDecimal getCashAmountSum() {
		return cashAmountSum;
	}

	public void setCashAmountSum(BigDecimal cashAmountSum) {
		this.cashAmountSum = cashAmountSum;
	}

	public BigDecimal getGiftAmountSum() {
		return giftAmountSum;
	}

	public void setGiftAmountSum(BigDecimal giftAmountSum) {
		this.giftAmountSum = giftAmountSum;
	}

	public BigDecimal getNoBenefitAmountSum() {
		return noBenefitAmountSum;
	}

	public void setNoBenefitAmountSum(BigDecimal noBenefitAmountSum) {
		this.noBenefitAmountSum = noBenefitAmountSum;
	}
	
}