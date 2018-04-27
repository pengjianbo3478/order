package com.gl365.order.fft.smzf.bo;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.order.dto.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 付费通扫码支付接口--请求或者应答报文公共报文头
 */
@XStreamAlias("head")
public class XmlHead extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6468901899013037066L;
	/**
	 * <p>版本号</p>
	 * <p>目前版本“1.0.0”</p>
	 */
	private String version = "1.0.0";
	/**
	 * <p>报文类型</p>
	 * <p>合作方(给乐)相关报文：01</p>
	 * <p>付费通平台相关报文：02</p>
	 */
	private String msgType = "01";
	/**
	 * <p>请求日期时间</p>
	 * <p>格式为yyyyMMddHHmmss</p>
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime reqDate = LocalDateTime.now();
	/**
	 * <p>平台返回流水号</p>
	 */
	private String smzfMsgId;
	/**
	 * <p>应答日期时间</p>
	 * <p>格式为yyyyMMddHHmmss</p>
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime respDate;
	/**
	 * <p>应答类型</p>
	 * <p>S：成功 E：失败 R：不确定（处理中）</p>
	 * 
	 */
	private String respType;
	/**
	 * <p>应答码</p>
	 * <p>成功：000000 补单成功：000090 失败：返回具体的响应码</p>
	 * 
	 */
	private String respCode;
	/**
	 * <p>应答描述</p>
	 * <p>对应应答码信息描述，包含中文</p>
	 */
	private String respMsg;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public LocalDateTime getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDateTime reqDate) {
		this.reqDate = reqDate;
	}

	public String getSmzfMsgId() {
		return smzfMsgId;
	}

	public void setSmzfMsgId(String smzfMsgId) {
		this.smzfMsgId = smzfMsgId;
	}

	public LocalDateTime getRespDate() {
		return respDate;
	}

	public void setRespDate(LocalDateTime respDate) {
		this.respDate = respDate;
	}

	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	
}
