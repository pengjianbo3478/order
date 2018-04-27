package com.gl365.order.fft.controller.merchant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftBARQ01;
import com.gl365.order.dto.req.customer.FftMER01;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftBARP01Result;
import com.gl365.order.dto.rsp.customer.FftMER01Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.MerchantService;
import com.gl365.order.fft.service.PayService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通商户进件接口：FftMER01 条码支付消费请求
 */
@RestController
public class MerchantAuthorizationReqController extends BaseController<FftMER01>{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantAuthorizationReqController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@PostMapping(value = "merchantAuthorizationRequest")
	@Override
	@ApiOperation(value = "付费通商户进件接口", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftMER01Result.class)
	public FftResult doAction(@RequestBody FftMER01 req){
		logger.info("merchantAuthorizationRequest 付费通商户进件接口, req:{}",req.toString());
		FftResult fftMER01Result = null;;
		try {
			fftMER01Result = this.merchantService.merchantAuthorizationReq(req);
		} catch (Exception e) {
			logger.error("MerchantAuthorizationReq ===> merchantService.merchantAuthorizationReq exception,e:"+e);
			fftMER01Result = FftResult.error();
		}
		logger.info("merchantAuthorizationReq end,fftBARQ01Result:{}",fftMER01Result.toString());
		return fftMER01Result;
	}

}
