package com.gl365.order.fft.core;

/**
 * 付费通加解密处理
 *@author summer
 *@date:2017年6月15日 上午11:49:15
 */
public interface MessageHandlerApi<T,R>{
	/**
	 *执行http访问请求
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 上午11:51:49
	 */
	String execute(String url,T fftSmzfRequest ) throws Exception;

	/**
	 * <p>XML报文组装请求数据</p>
	 * @param xmlRequestMessage
	 * @param fftSmzfRequest
	 * @return
	 * @throws Exception
	 */
	void buildFftReqeustMessage(String xmlRequestMessage, T fftSmzfRequest) throws Exception;
	
	
	
	/**
	 * <p>获取返回XML报文</p>
	 * @param fftResponseMessage
	 * @return String
	 * @throws Exception
	 */
	String getXmlResponseMessage(R fftResponseMessage) throws Exception;
}
