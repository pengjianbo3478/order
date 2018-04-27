package com.gl365.order.fft.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl365.order.dto.req.customer.FftBARC01;
import com.gl365.order.dto.req.customer.FftBARP01;
import com.gl365.order.dto.req.customer.FftBARQ01;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftBARC01Result;
import com.gl365.order.dto.rsp.customer.FftBARP01Result;
import com.gl365.order.dto.rsp.customer.FftBARQ01Result;
import com.gl365.order.enums.FftSmzfTranCode;
import com.gl365.order.fft.service.BaseService;
import com.gl365.order.fft.service.PayService;
import com.gl365.order.fft.smzf.bo.XmlResponse;

@Service
public class PayServiceImpl extends BaseService implements PayService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PayServiceImpl.class);
	
	@Override
	public FftResult barCodePayReq(FftBARP01 fftBARP01) throws Exception {
		XmlResponse xmlResponse =  httpPostXmlResp(fftBARP01, FftSmzfTranCode.BARCODE_PAY_B, null);
		return copyF2B(xmlResponse, FftBARP01Result.class);
	}


	/**
	  * 构建支付标题
	  * $@订单类型,原订单号，被接收员工id@$
	  * @param orderType 订单类型：1、正常订单     2、打赏订单     3、达人订单    4、网购订单
	  * @param oriPayId  原订单号
	  * @param rewardUserId 接收员工ID
	  * @return
	  */
	 private static String buildPayTitle(String orderType, String oriPayId, String rewardUserId, String title){		
		 String subject ="$@%s,%s,%s@$%s";
		 subject = String.format(subject, orderType,oriPayId,rewardUserId,title);
		 return subject;
	 }


	@Override
	public FftResult barPayQueryReq(FftBARQ01 fftBARQ01) throws Exception {
		XmlResponse xmlResponse =  httpPostXmlResp(fftBARQ01, FftSmzfTranCode.BARCODE_PAY_QUERY, null);
		return copyF2B(xmlResponse, FftBARQ01Result.class);
	}


	@Override
	public FftResult barPayCancelReq(FftBARC01 fftBARC01) throws Exception {
		XmlResponse xmlResponse =  httpPostXmlResp(fftBARC01, FftSmzfTranCode.BARCODE_PAY_CANCEL, null);
		return copyF2B(xmlResponse, FftBARC01Result.class);
	}
	

}
