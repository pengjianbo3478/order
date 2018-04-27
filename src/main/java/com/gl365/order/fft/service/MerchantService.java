package com.gl365.order.fft.service;

import com.gl365.order.dto.req.customer.FftMER01;
import com.gl365.order.dto.req.customer.FftMER02;
import com.gl365.order.dto.req.customer.FftMER03;
import com.gl365.order.dto.req.customer.FftMER04;
import com.gl365.order.dto.rsp.FftResult;

/**
 * 进件相关业务 与付费通交互
 * @author dfs_523
 *2017年6月17日上午10:43:50
 */
public interface MerchantService {
	
	/**
	 * 商户进件(MER01)
	 * @param FftMER01
	 * @return
	 * @throws Exception
	 */
	FftResult merchantAuthorizationReq(FftMER01 fftMER01) throws Exception;
	
	/**
	 * 商户查询(FftMER02)
	 * @param FftMER02
	 * @return
	 * @throws Exception
	 */
	FftResult merchantQueryReq(FftMER02 fftMER02) throws Exception;
	
	/**
	 * 商户修改(FftMER03)
	 * @param FftMER03
	 * @return
	 * @throws Exception
	 */
	FftResult merchantUpdateReq(FftMER03 fftMER03) throws Exception;
	
	/**
	 * 商户增加终端(FftMER03)
	 * @param FftMER03
	 * @return
	 * @throws Exception
	 */
	FftResult MerchantAddTerminalReq(FftMER04 fftMER04) throws Exception;
	
}
