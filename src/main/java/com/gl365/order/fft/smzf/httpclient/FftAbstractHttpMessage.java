package com.gl365.order.fft.smzf.httpclient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.gl365.order.fft.smzf.bo.FftRequestMessage;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;
import com.gl365.order.util.JsonUtil;

public abstract class FftAbstractHttpMessage extends FftHttpMessageSecurity implements FftHttpMessageService {
	private static final Logger LOG = LoggerFactory.getLogger(FftAbstractHttpMessage.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String execute(String url, FftRequestMessage fftSmzfRequest) throws Exception {
		LOG.info("======>付费通请求url:{},请求参数：{}",url,fftSmzfRequest);
 		String jsonStr = restTemplate.postForObject(url, fftSmzfRequest, String.class);
		LOG.info("======>付费通返回加密结果:{}",jsonStr);
		FftResponseMessage fftResponseMessage = JsonUtil.fromJsonStr(jsonStr, FftResponseMessage.class);
		String xmlResponseMessage = this.getXmlResponseMessage(fftResponseMessage);
		LOG.info("======>付费通返回解析xml结果:{}",xmlResponseMessage);
		return xmlResponseMessage;
	}

	@Override
	public String getXmlResponseMessage(FftResponseMessage fftResponseMessage) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("解析返回结果开始...");
		}
		String aesKey = this.getRespAesKey(fftResponseMessage);
		String encryptData = fftResponseMessage.getEncryptData();
		String xmlResponseData = this.getRespData(aesKey, encryptData);
		String signData = fftResponseMessage.getSignData();
		boolean result = checkSignFromBase64(xmlResponseData, signData);
		if (!result)
			throw new Exception("用合作方(FFT)RSA公钥验证签名失败");
		LOG.debug("解析返回结果完成...");
		return xmlResponseData;
	}

	@Override
	public void buildFftReqeustMessage(String xmlRequestMessage, FftRequestMessage fftSmzfRequest) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("组装请求报文开始....");
		}
		if(fftSmzfRequest == null){
			fftSmzfRequest = new FftRequestMessage();
		}
		String encryptData = this.getEncryptData(xmlRequestMessage);
		LOG.debug("encryptData={}", encryptData);
		fftSmzfRequest.setEncryptData(encryptData);
		fftSmzfRequest.setCooperator(this.getCooperator());
		String signData = this.getSignData(xmlRequestMessage);
		LOG.debug("signData={}", signData);
		fftSmzfRequest.setSignData(signData);
		String encryptKey = this.getEncryptKey();
		LOG.debug("encryptKey={}", encryptKey);
		fftSmzfRequest.setEncryptKey(encryptKey);
		fftSmzfRequest.setExt(StringUtils.EMPTY);
		LOG.debug("组装请求报文完成....");
	}
	
}
