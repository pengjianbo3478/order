package com.gl365.order.fft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.order.common.Properties;
import com.gl365.order.dto.req.FftHead;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.enums.ClassType;
import com.gl365.order.enums.FftSmzfTranCode;
import com.gl365.order.fft.smzf.bo.FftRequestMessage;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;
import com.gl365.order.fft.smzf.bo.XmlBody;
import com.gl365.order.fft.smzf.bo.XmlHead;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.httpclient.FftTranTemplate;
import com.gl365.order.util.XmlUtils;


@Service
public class BaseService {
	private static final Logger log = LoggerFactory.getLogger(BaseService.class);
	
	@Autowired
	protected Properties props;
	
	@Autowired
	protected FftTranTemplate tranTemplate ;
	
	/**
	 *付费通请求直接返回业务对象
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午4:08:41
	 */
	public  <T extends FftResult>T httpPostResult(FftHead params,FftSmzfTranCode code,String reqMsgId) throws Exception{
		Class<? extends FftResult> clazz = tranTemplate.getClassByCode(code, ClassType.RESULT);
		XmlResponse xmlResponse = httpPostXmlResp(params, code,reqMsgId);
		T t = (T) copyF2B(xmlResponse, clazz);
		return t;
	}
	
	/**
	 *付费通请求直接返回业务对象（随机生成流水号）
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午4:09:04
	 */
	public  <T extends FftResult>T httpPostResult(FftHead params,FftSmzfTranCode code) throws Exception{
		return httpPostResult(params, code,null);
	}
	
	/**
	 *付费通请求返回xmlBean对象（随机生成流水号）
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午4:09:11
	 */
	public <T extends XmlResponse>T httpPostXmlResp(FftHead params,FftSmzfTranCode code) throws Exception{
		return httpPostXmlResp(params, code,null);
	}
	
	/**
	 *付费通请求返回xmlBean对象
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午4:09:41
	 */
	public <T extends XmlResponse>T httpPostXmlResp(FftHead params,FftSmzfTranCode code,String reqMsgId) throws Exception{
		String url = props.getFftUrl()+"/api.do";
		return httpPostXmlResp(url, params, code,reqMsgId);
	}
	
	/**
	 * 请求付费通接口 初始化请求对象
	 *summer
	 *2017年6月8日 下午4:15:03
	 * @throws Exception 
	 */
	private  <T extends XmlResponse>T httpPostXmlResp(String url,FftHead params,FftSmzfTranCode code,String reqMsgId) throws Exception{
		log.debug("=====>调用付费通接口,url:{},交易码：{},请求参数：{}",url,code.getDesc(), params);
		FftRequestMessage fftSmzfRequest = tranTemplate.buildFftRequestMessage(params, code,reqMsgId);
		Class<? extends XmlResponse> cls = tranTemplate.getClassByCode(code, ClassType.RESPONSE);
		if(fftSmzfRequest != null){
			String xmlResponseMessage = tranTemplate.execute(url, fftSmzfRequest);
			T resp = (T) XmlUtils.xmlToBean(xmlResponseMessage, cls);
			log.info("=====>调用付费通接口，交易码：{},返回响应xml结果：{}",code.getDesc(),resp);
			return resp;
		}
		return null;
	}
	
	/**
	 *把付费通xml报文转为业务对象
	 *@param
	 *@author:summer
	 *@date:2017年6月14日 下午4:19:54
	 */
	protected <T extends FftResult>FftResult copyF2B(XmlResponse xmlResponse ,Class<T> cls){
		FftResult t = null;
		try {
			t = cls.newInstance();
			XmlHead xmlHead = xmlResponse.getXmlHead();
			XmlBody xmlBody = xmlResponse.getXmlBody();
			if(xmlHead != null){
				BeanUtils.copyProperties(xmlHead, t);
			}
			if(xmlBody != null){
				BeanUtils.copyProperties(xmlBody, t);
			}
		} catch (Exception  e) {
			return  FftResult.error();
		}
		return t;
	}
	
	/**
	 *从返回结果解密出业务对象
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午12:21:22
	 */
	protected <T extends FftResult,R extends XmlResponse >FftResult copyF2B(FftResponseMessage fftResponseMessage ,Class<R> rspType,Class<T> resultType){
		XmlResponse xmlResponse = null;
		try {
			xmlResponse = tranTemplate.doResponse(fftResponseMessage, rspType);
			if(xmlResponse == null){
				return  FftResult.error();
			}
		} catch (Exception e1) {
			return  FftResult.error();
		}
		return copyF2B(xmlResponse, resultType);
	}
	
}
