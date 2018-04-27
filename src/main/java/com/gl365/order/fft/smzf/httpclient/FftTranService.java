package com.gl365.order.fft.smzf.httpclient;
import com.gl365.order.dto.req.FftHead;
import com.gl365.order.enums.FftSmzfTranCode;
import com.gl365.order.fft.smzf.bo.FftRequestMessage;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;
public interface FftTranService {
	
	<T> T post(String url, Object request, FftRequestMessage fftSmzfRequest, Class<T> responseType) throws Exception;

	/**
	 * <p>组装xml报文</p>
	 * @param request 请求参数
	 * @return String
	 */
	String buildXmlRequestMessage(Object request);

	/**
	 *组装请求参数对象
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 下午12:10:40
	 */
	FftRequestMessage buildFftRequestMessage(FftHead params,FftSmzfTranCode code,String reqMsgId);
	
	/**
	 * 
	 * <p>解析报文 </p>
	 * @param xmlResponseMessage
	 * @param responseType
	 * @return
	 */
	<T> T decompose(String xmlResponseMessage, Class<T> responseType);
	
	/**
	 * 
	 * <p>解析付费通加密报文 </p>
	 * @param fftResponseMessage
	 * @param responseType
	 * @return
	 */
	<T> T doResponse(FftResponseMessage fftResponseMessage, Class<T> responseType) throws Exception;
	
	/**
	 * <p>获取返回XML报文</p>
	 * @param fftResponseMessage
	 * @return String
	 * @throws Exception
	 */
	String getXmlResponseMessage(FftResponseMessage fftResponseMessage) throws Exception;
	
}
