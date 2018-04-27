package com.gl365.order.fft.smzf.httpclient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.ClassType;
import com.gl365.order.enums.FftSmzfTranCode;
import com.gl365.order.fft.smzf.bo.FftRequestMessage;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;
import com.gl365.order.fft.smzf.bo.XmlRequest;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyBARC01;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyBARP01;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyBARQ01;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyMER01;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyMER02;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyMER03;
import com.gl365.order.fft.smzf.xml.request.body.XmlReqBodyMER04;
import com.gl365.order.fft.smzf.xml.request.body.XmlRequestMER01;
import com.gl365.order.fft.smzf.xml.request.body.XmlRequestMER04;
import com.gl365.order.fft.smzf.xml.response.XmlRespBARC01;
import com.gl365.order.fft.smzf.xml.response.XmlRespBARP01;
import com.gl365.order.fft.smzf.xml.response.XmlRespBARQ01;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER01;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER02;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER03;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER04;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARC01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARP01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyBARQ01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER01;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER02;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER03;
import com.gl365.order.fft.smzf.xml.response.body.XmlRespBodyMER04;
import com.gl365.order.util.RandomUtils;
import com.gl365.order.util.XmlUtils;
@Component
public class FftTranTemplate extends FftAbstractHttpMessage implements FftTranService {
	private static final Logger LOG = LoggerFactory.getLogger(FftTranTemplate.class);
	
	/**
	 * 与付费通交易
	 */
	@Override
	public <T> T post(String url, Object request, FftRequestMessage fftSmzfRequest, Class<T> responseType) throws Exception {
		Assert.notNull(url, "'url' must not be null");
		Assert.notNull(request, "'request' must not be null");
		Assert.notNull(fftSmzfRequest, "'fftSmzfRequest' must not be null");
		Assert.notNull(responseType, "'responseType' must not be null");
		//组装xml请求报文
		String xmlReqeustMessage = this.buildXmlRequestMessage(request);
		this.buildFftReqeustMessage(xmlReqeustMessage, fftSmzfRequest);
		LOG.info("=====>xmlReqeustMessage:\n{}", xmlReqeustMessage);
		String xmlResponseMessage = this.execute(url, fftSmzfRequest);
		LOG.info("=====>xmlResponseMessage:{}", xmlResponseMessage);
		T t = this.decompose(xmlResponseMessage, responseType);
		LOG.info("=======返回结果解析，result={}",t);
		return t;
	}
	
	@Override
	public <T> T doResponse(FftResponseMessage fftResponseMessage, Class<T> responseType) throws Exception {
		Assert.notNull(fftResponseMessage, "'fftResponseMessage' must not be null");
		Assert.notNull(responseType, "'responseType' must not be null");
		String xmlResponseMessage = this.getXmlResponseMessage(fftResponseMessage);		
		LOG.debug("=====>返回xml结果，xmlResponseMessage=\n{}", xmlResponseMessage);
		T t = this.decompose(xmlResponseMessage, responseType);
		LOG.info("=====>付费通返回结果，result:{}", t);
		return t;
	}
	
	@Override
	public String buildXmlRequestMessage(Object request) {
		String xmlContent = XmlUtils.beanToXml(request);
		LOG.info("=====>转换xml报文：{}", xmlContent);
		return xmlContent;
	}
	
	public FftRequestMessage buildFftReqeustMessage(XmlRequest<?> request, FftSmzfTranCode code,String reqMsgId) throws Exception {
		String xmlContext = this.buildXmlRequestMessage(request);
		FftRequestMessage fftSmzfRequest = new FftRequestMessage();
		fftSmzfRequest.setTranCode(code.getCode());
		//if(StringUtils.isNullOrEmpty(reqMsgId)){
			if(StringUtils.isBlank(reqMsgId)){
			fftSmzfRequest.setReqMsgId(RandomUtils.getRandomReqMsgId());
		}else{
			fftSmzfRequest.setReqMsgId(reqMsgId);
		}
		this.buildFftReqeustMessage(xmlContext, fftSmzfRequest);
		return fftSmzfRequest;
	}

	public FftRequestMessage buildFftReqeustMessage(XmlRequest<?> request, FftSmzfTranCode code) throws Exception {
		return buildFftReqeustMessage(request, code, null);
	}
	
	@Override
	public <T> T decompose(String xmlResponseMessage, Class<T> responseType) {
		return XmlUtils.xmlToBean(xmlResponseMessage, responseType);
	}

	public <T>T  getClassByCode(FftSmzfTranCode code ,ClassType type){
		XmlRequest<?> req = null;
		Class<?> cls = null;
		Class<?> cls2 = null;
		switch (code) {
		case BARCODE_PAY_B:
			req = new XmlRequest<XmlReqBodyBARP01>(XmlReqBodyBARP01.class);
			cls = XmlRespBARP01.class;
			cls2 = XmlRespBodyBARP01.class;
			break;
		case BARCODE_PAY_QUERY:
			req = new XmlRequest<XmlReqBodyBARQ01>(XmlReqBodyBARQ01.class);
			cls = XmlRespBARQ01.class;
			cls2 = XmlRespBodyBARQ01.class;
			break;
		case BARCODE_PAY_CANCEL:
			req = new XmlRequest<XmlReqBodyBARC01>(XmlReqBodyBARC01.class);
			cls = XmlRespBARC01.class;
			cls2 = XmlRespBodyBARC01.class;
			break;
		case MERCHANT_AUTHORIZATION:
			req = new XmlRequestMER01();
			cls = XmlRespMER01.class;
			cls2 = XmlRespBodyMER01.class;
			break;
		case MERCHANT_QUERY:
			req = new XmlRequest<XmlReqBodyMER02>(XmlReqBodyMER02.class);
			cls = XmlRespMER02.class;
			cls2 = XmlRespBodyMER02.class;
			break;
		case MERCHANT_UPDATE:
			req = new XmlRequest<XmlReqBodyMER03>(XmlReqBodyMER03.class);
			cls = XmlRespMER03.class;
			cls2 = XmlRespBodyMER03.class;
			break;
		case MERCHANT_ADD_TERMINAL:
			req = new XmlRequestMER04();
			cls = XmlRespMER04.class;
			cls2 = XmlRespBodyMER04.class;
			break;
		
		default:
		}
		switch(type){
		case REQUEST:
			return (T) req;
		case RESPONSE:
			return (T) cls;
		case RESPBODY:
			return (T) cls2;
			default :
				throw new RuntimeException();
		}
	}

	public FftRequestMessage buildFftRequestMessage(FftHead params,FftSmzfTranCode code){
		return this.buildFftRequestMessage(params, code, null);
	}
	
	public FftRequestMessage buildFftRequestMessage(FftHead params, FftSmzfTranCode code, String reqMsgId) {
		XmlRequest<?> req = getClassByCode(code, ClassType.REQUEST);
		if(params != null){
			params.setReqDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss")));
		}
		if(req != null){
			BeanUtils.copyProperties(params, req.getXmlHead());
			BeanUtils.copyProperties(params, req.getXmlBody());
			try {
				FftRequestMessage fftSmzfRequest = buildFftReqeustMessage(req, code, reqMsgId);
				return fftSmzfRequest;
			} catch (Exception e) {
				LOG.error("=====<组装请求报文异常",e);
			}
		}
		return null;
	}
	
}
