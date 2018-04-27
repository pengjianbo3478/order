package com.gl365.order.fft.smzf.bo;

import com.gl365.order.dto.BaseEntity;

/**
 * 与付费通交互结构体
 * @author summer
 *
 */
public class FftMessage extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922767284158322366L;

	/**
	 * <p>加密后的应答报文</p>
	 */
	private String encryptData;
	/**
	 * <p>加密后的AES对称密钥</p>
	 */
	private String encryptKey;
	/**
	 * <p>应答报文签名</p>
	 */
	private String signData;
	
	/**
	 * <p>请求流水号</p>
	 */
	private String reqMsgId;
	/**
	 * <p>备用域</p>
	 */
	private String ext;
	
	public FftMessage() {
		super();
	}
	
	public FftMessage(FftMessage message) {
		this.encryptData = message.getEncryptData();
		this.encryptKey = message.getEncryptKey();
		this.reqMsgId = message.getReqMsgId();
		this.signData = message.getSignData();
		this.ext = message.getExt();
	}
	

	public String getEncryptData() {
		return encryptData;
	}

	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public String getSignData() {
		return signData;
	}

	public void setSignData(String signData) {
		this.signData = signData;
	}

	public String getReqMsgId() {
		return reqMsgId;
	}

	public void setReqMsgId(String reqMsgId) {
		this.reqMsgId = reqMsgId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
}
