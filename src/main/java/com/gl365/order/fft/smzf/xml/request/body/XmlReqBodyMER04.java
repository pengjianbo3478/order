package com.gl365.order.fft.smzf.xml.request.body;


import java.util.List;

import com.gl365.order.dto.req.customer.TidInfo;
import com.gl365.order.enums.TransCode;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 付费通新增终端请求 MER04
 */
@XStreamAlias("body")
public class XmlReqBodyMER04 extends XmlBody {


	private String transCode =TransCode.MER04.getValue(); 
	
	private String orgCode;
	
	private List<TidInfo> tidInfos;

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<TidInfo> getTidInfos() {
		return tidInfos;
	}

	public void setTidInfos(List<TidInfo> tidInfos) {
		this.tidInfos = tidInfos;
	}

}
