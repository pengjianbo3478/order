package com.gl365.order.remote.dto;

import com.gl365.order.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/9/14.
 */
public class QueryOrderDetail implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * amount : 0.02
     * decStatus : null
     * payResult : 0
     * cpOrderNo : 10006000000000517091319045929420
     * decAmount : 0.01
     * transTime : 20170913190459
     * settleDetails : [{"freezeDays":0,"amount":0.01,"virAccNo":"41u0983iueo8r187432o1","settleTime":null,"innerOrderNo":"REF-17091319112126040","settleResult":0},{"freezeDays":0,"amount":0.01,"virAccNo":"41u0983iueo8r187432o1","settleTime":null,"innerOrderNo":"REF-17091319121416582","settleResult":0}]
     * mchOrderNo : 20170913162703
     * mchNo : 100060000000005
     */

    private double amount;
    private Object decStatus;
    //支付状态
    //0：未支付
    //1：已支付
    //2：退款处理中
    //3：已冲正
    //4：已撤销
    //5：支付失败
    //7：已退款
    private String payResult;
    private String cpOrderNo;
    private double decAmount;
    private String transTime;
    private String mchOrderNo;
    private String mchNo;
    private String payOrderNo;
    private List<SettleDetail> settleDetails;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Object getDecStatus() {
        return decStatus;
    }

    public void setDecStatus(Object decStatus) {
        this.decStatus = decStatus;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getCpOrderNo() {
        return cpOrderNo;
    }

    public void setCpOrderNo(String cpOrderNo) {
        this.cpOrderNo = cpOrderNo;
    }

    public double getDecAmount() {
        return decAmount;
    }

    public void setDecAmount(double decAmount) {
        this.decAmount = decAmount;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getMchOrderNo() {
        return mchOrderNo;
    }

    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public List<SettleDetail> getSettleDetails() {
        return settleDetails;
    }

    public void setSettleDetails(List<SettleDetail> settleDetails) {
        this.settleDetails = settleDetails;
    }
    
	public String toString() {
		return JsonUtil.fromObject(this);
	}
	
	

    public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}



	public static class SettleDetail {
        /**
         * freezeDays : 0
         * amount : 0.01
         * virAccNo : 41u0983iueo8r187432o1
         * settleTime : null
         * innerOrderNo : REF-17091319112126040
         * settleResult : 0
         */

        private String virAccNo;
        private Object settleTime;
        private String innerOrderNo;
        private int settleResult;

        public String getVirAccNo() {
            return virAccNo;
        }

        public void setVirAccNo(String virAccNo) {
            this.virAccNo = virAccNo;
        }

        public Object getSettleTime() {
            return settleTime;
        }

        public void setSettleTime(Object settleTime) {
            this.settleTime = settleTime;
        }

        public String getInnerOrderNo() {
            return innerOrderNo;
        }

        public void setInnerOrderNo(String innerOrderNo) {
            this.innerOrderNo = innerOrderNo;
        }

        public int getSettleResult() {
            return settleResult;
        }

        public void setSettleResult(int settleResult) {
            this.settleResult = settleResult;
        }
    }
}
