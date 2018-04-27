package com.gl365.order.fft.smzf.httpclient;
import com.gl365.order.fft.smzf.bo.FftRequestMessage;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;
/**
 * 付费通交互业务
 *@author summer
 *@date:2017年6月15日 上午11:49:15
 */
public interface FftHttpMessageService {
	/**
	 *执行http访问请求
	 *@param
	 *@author:summer
	 *@date:2017年6月15日 上午11:51:49
	 */
	String execute(String url,FftRequestMessage fftSmzfRequest) throws Exception;

	/**
	 * <p>XML报文组装请求数据</p>
	 * @param xmlRequestMessage
	 * @param fftSmzfRequest
	 * @return
	 * @throws Exception
	 */
	void buildFftReqeustMessage(String xmlRequestMessage, FftRequestMessage fftSmzfRequest) throws Exception;
	
	
	
	/**
	 * <p>获取返回XML报文</p>
	 * @param fftResponseMessage
	 * @return String
	 * @throws Exception
	 */
	String getXmlResponseMessage(FftResponseMessage fftResponseMessage) throws Exception;
}
