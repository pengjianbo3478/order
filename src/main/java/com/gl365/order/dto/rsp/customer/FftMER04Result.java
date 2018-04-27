package com.gl365.order.dto.rsp.customer;

import java.util.List;

import com.gl365.order.dto.req.customer.TidInfo;
import com.gl365.order.dto.rsp.FftResult;

import io.swagger.annotations.ApiModelProperty;

/**
 * 付费通新增终端(MER04)响应报文参数信息
 * @author dfs_519
 *2017年6月17日下午12:09:41
 */
public class FftMER04Result extends FftResult {

	private static final long serialVersionUID = -307849208336478505L;
	/**
	 * 业务应答码
	 */
	private String rltCode;
	/**
	 * 业务应答内容
	 */
	private String rltMsg;
	
	private List<TidInfo> tidInfos;
	
	public List<TidInfo> getTidInfos() {
		return tidInfos;
	}
	public void setTidInfos(List<TidInfo> tidInfos) {
		this.tidInfos = tidInfos;
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
