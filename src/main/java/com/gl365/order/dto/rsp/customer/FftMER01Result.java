package com.gl365.order.dto.rsp.customer;

import com.gl365.order.dto.req.customer.PosBusiness;
import com.gl365.order.dto.rsp.FftResult;

/**
 * 付费通商户进件 (MER01)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftMER01Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;
	/**
	 * 付费通商户号，成功进件后应答
	 */
	private String orgCode;
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
	
	private PosBusiness posBusiness;
	
	public PosBusiness getPosBusiness() {
		return posBusiness;
	}
	public void setPosBusiness(PosBusiness posBusiness) {
		this.posBusiness = posBusiness;
	}
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	
	
}
