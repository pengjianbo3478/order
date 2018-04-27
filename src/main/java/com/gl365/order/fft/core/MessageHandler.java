package com.gl365.order.fft.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.gl365.order.fft.smzf.bo.FftMessage;
import com.gl365.order.fft.smzf.bo.FftReqMessage;
import com.gl365.order.util.JsonUtil;


public abstract class MessageHandler<T,R> implements MessageHandlerApi<T,R> {
	private static final Logger LOG = LoggerFactory.getLogger(MessageHandler.class);
	@Autowired
	private RestTemplate restTemplate;
	
	public abstract String execute(String url,T fftSmzfRequest) throws Exception;

	public abstract void buildFftReqeustMessage(String xmlRequestMessage, T fftSmzfRequest) throws Exception;
	
	public abstract String getXmlResponseMessage(R fftResponseMessage) throws Exception;
	
	public String execute(String url, FftReqMessage fftSmzfRequest ,SecurityHandler security) throws Exception {
		LOG.info("======>付费通请求url:{},请求参数：{}",url,fftSmzfRequest);
		String jsonStr = restTemplate.postForObject(url, fftSmzfRequest, String.class);
		LOG.info("======>付费通返回加密结果:{}",jsonStr);
		FftMessage fftResponseMessage = JsonUtil.fromJsonStr(jsonStr, FftMessage.class);
		String xmlResponseMessage = this.getXmlResponseMessage(fftResponseMessage,security);
		LOG.info("======>付费通返回解析xml结果:{}",xmlResponseMessage);
		return xmlResponseMessage;
	}

	public String getXmlResponseMessage(FftMessage fftResponseMessage , SecurityHandler security) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("解析返回结果开始...");
		}
		String encryptKey = fftResponseMessage.getEncryptData();
		String aesKey = security.getRespAesKey(encryptKey);
		String encryptData = fftResponseMessage.getEncryptData();
		String xmlResponseData = security.getRespData(aesKey, encryptData);
		String signData = fftResponseMessage.getSignData();
		boolean result = security.checkSignFromBase64(xmlResponseData, signData);
		if (!result)
			throw new Exception("用合作方(FFT)RSA公钥验证签名失败");
		LOG.debug("解析返回结果完成...");
		return xmlResponseData;
	}

	public void buildFftReqeustMessage(String xmlRequestMessage, FftReqMessage fftSmzfRequest,SecurityHandler security) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("组装请求报文开始....");
		}
		if(fftSmzfRequest == null){
			fftSmzfRequest = new FftReqMessage();
		}
		String encryptData = security.getEncryptData(xmlRequestMessage);
		LOG.debug("encryptData={}", encryptData);
		fftSmzfRequest.setEncryptData(encryptData);
		fftSmzfRequest.setCooperator(security.getCooperator());
		String signData = security.getSignData(xmlRequestMessage);
		LOG.debug("signData={}", signData);
		fftSmzfRequest.setSignData(signData);
		String encryptKey = security.getEncryptKey();
		LOG.debug("encryptKey={}", encryptKey);
		fftSmzfRequest.setEncryptKey(encryptKey);
		fftSmzfRequest.setExt(StringUtils.EMPTY);
		LOG.debug("组装请求报文完成....");
	}
	
	
	
}
