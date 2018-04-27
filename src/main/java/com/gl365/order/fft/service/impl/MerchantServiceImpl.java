package com.gl365.order.fft.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl365.order.dto.req.customer.FftMER01;
import com.gl365.order.dto.req.customer.FftMER02;
import com.gl365.order.dto.req.customer.FftMER03;
import com.gl365.order.dto.req.customer.FftMER04;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftMER01Result;
import com.gl365.order.dto.rsp.customer.FftMER02Result;
import com.gl365.order.dto.rsp.customer.FftMER03Result;
import com.gl365.order.dto.rsp.customer.FftMER04Result;
import com.gl365.order.enums.FftSmzfTranCode;
import com.gl365.order.fft.service.BaseService;
import com.gl365.order.fft.service.MerchantService;
import com.gl365.order.fft.smzf.bo.XmlResponse;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER01;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER02;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER03;
import com.gl365.order.fft.smzf.xml.response.XmlRespMER04;

@Service
public class MerchantServiceImpl extends BaseService implements MerchantService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MerchantServiceImpl.class);

	@Override
	public FftResult merchantAuthorizationReq(FftMER01 fftMER01) throws Exception {
		XmlRespMER01 xmlResponse = (XmlRespMER01)httpPostXmlResp(fftMER01, FftSmzfTranCode.MERCHANT_AUTHORIZATION, null);
		FftMER01Result fftMER01Result = (FftMER01Result)copyF2B(xmlResponse, FftMER01Result.class);
		fftMER01Result.setRespCode(xmlResponse.getXmlHead().getRespCode());
		fftMER01Result.setRespMsg(xmlResponse.getXmlHead().getRespMsg());
		fftMER01Result.setRltCode(xmlResponse.getXmlBody().getRespCode());
		fftMER01Result.setRltMsg(xmlResponse.getXmlBody().getRespMsg());
		return fftMER01Result;
	}

	@Override
	public FftResult merchantQueryReq(FftMER02 fftMER02) throws Exception {
		XmlRespMER02 xmlResponse =  (XmlRespMER02)httpPostXmlResp(fftMER02, FftSmzfTranCode.MERCHANT_QUERY, null);
		FftMER02Result fftMER02Result = (FftMER02Result)copyF2B(xmlResponse, FftMER02Result.class);
		fftMER02Result.setRespCode(xmlResponse.getXmlHead().getRespCode());
		fftMER02Result.setRespMsg(xmlResponse.getXmlHead().getRespMsg());
		fftMER02Result.setRltCode(xmlResponse.getXmlBody().getRespCode());
		fftMER02Result.setRltMsg(xmlResponse.getXmlBody().getRespMsg());
		return fftMER02Result;
	}

	@Override
	public FftResult merchantUpdateReq(FftMER03 fftMER03) throws Exception {
		XmlRespMER03 xmlResponse =  (XmlRespMER03)httpPostXmlResp(fftMER03, FftSmzfTranCode.MERCHANT_UPDATE, null);
		FftMER03Result fftMER03Result = (FftMER03Result)copyF2B(xmlResponse, FftMER03Result.class);
		fftMER03Result.setRespCode(xmlResponse.getXmlHead().getRespCode());
		fftMER03Result.setRespMsg(xmlResponse.getXmlHead().getRespMsg());
		fftMER03Result.setRltCode(xmlResponse.getXmlBody().getRespCode());
		fftMER03Result.setRltMsg(xmlResponse.getXmlBody().getRespMsg());
		return fftMER03Result;
	}

	@Override
	public FftResult MerchantAddTerminalReq(FftMER04 fftMER04) throws Exception {
		XmlRespMER04 xmlResponse =  (XmlRespMER04)httpPostXmlResp(fftMER04, FftSmzfTranCode.MERCHANT_ADD_TERMINAL, null);
		FftMER04Result fftMER04Result = (FftMER04Result)copyF2B(xmlResponse, FftMER04Result.class);
		fftMER04Result.setRespCode(xmlResponse.getXmlHead().getRespCode());
		fftMER04Result.setRespMsg(xmlResponse.getXmlHead().getRespMsg());
		fftMER04Result.setRltCode(xmlResponse.getXmlBody().getRespCode());
		fftMER04Result.setRltMsg(xmlResponse.getXmlBody().getRespMsg());
		return fftMER04Result;
	}

}
