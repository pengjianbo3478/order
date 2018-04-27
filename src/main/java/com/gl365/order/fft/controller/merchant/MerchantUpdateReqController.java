package com.gl365.order.fft.controller.merchant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftMER03;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftMER03Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.MerchantService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通商户修改接口：BARP01 条码支付消费请求
 */
@RestController
public class MerchantUpdateReqController extends BaseController<FftMER03>{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantUpdateReqController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@PostMapping(value = "merchantUpdateRequest")
	@Override
	@ApiOperation(value = "商户修改", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftMER03Result.class)
	public FftResult doAction(@RequestBody FftMER03 req) {
		logger.info("MerchantUpdateReqController 商户修改,req:{}",req.toString());
		FftResult fftBARC01Result = null;
		try {
			fftBARC01Result = this.merchantService.merchantUpdateReq(req);
		} catch (Exception e) {
			logger.error("MerchantUpdateReqController ===> merchantService.merchantUpdateReq,e:"+e);
			fftBARC01Result = FftResult.error();
		}
		logger.info("merchantUpdateReq end,req:{}",fftBARC01Result.toString());
		return fftBARC01Result;
	}
}
