package com.gl365.order.dto.rsp.customer;

import com.gl365.order.dto.rsp.FftResult;

/**
 * 付费通商户修改(MER03)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftMER03Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;
	/**
	 * 应答码
	 */
	private String transCode;
	/**
	 * 业务应答码
	 */
	private String rltCode;
	/**
	 * 业务应答内容
	 */
	private String rltMsg;
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getRltCode() {
		return rltCode;
	}
	public void setRltCode(String rltCode) {
		this.rltCode = rltCode;
	}
	public String getRltMsg() {
		return rltMsg;
	}
	public void setRltMsg(String rltMsg) {
		this.rltMsg = rltMsg;
	}
	
}
