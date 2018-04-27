package com.gl365.order.fft.service;

import com.gl365.order.dto.req.customer.FftBARC01;
import com.gl365.order.dto.req.customer.FftBARP01;
import com.gl365.order.dto.req.customer.FftBARQ01;
import com.gl365.order.dto.rsp.FftResult;

/**
 * 支付相关业务 与付费通交互
 * @author dfs_519
 *2017年6月17日上午10:43:50
 */
public interface PayService {
	
	/**
	 * 条码支付消费请求(BARP01)
	 * @param fftBARP01
	 * @return
	 * @throws Exception
	 */
	FftResult barCodePayReq(FftBARP01 fftBARP01) throws Exception;
	
	/**
	 * 条码支付订单查询请求
	 * @param fftBARQ01
	 * @return
	 * @throws Exception
	 */
	FftResult barPayQueryReq(FftBARQ01 fftBARQ01)throws Exception;
	
	/**
	 * 条码支付撤单请求
	 * @param fftBARC01
	 * @return
	 * @throws Exception
	 */
	FftResult barPayCancelReq(FftBARC01 fftBARC01)throws Exception;
}
