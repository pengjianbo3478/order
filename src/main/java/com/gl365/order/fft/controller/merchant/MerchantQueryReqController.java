package com.gl365.order.fft.controller.merchant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftMER02;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftMER02Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.MerchantService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通商户查询接口：FftMER02 条码支付消费请求
 */
@RestController
public class MerchantQueryReqController extends BaseController<FftMER02>{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantQueryReqController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@PostMapping(value = "merchantQueryRequest")
	@Override
	@ApiOperation(value = "商户查询", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftMER02Result.class)
	public FftResult doAction(@RequestBody FftMER02 req) {
		logger.info("MerchantQueryReqController 商户查询,req:{}",req.toString());
		FftResult fftBARC01Result = null;
		try {
			fftBARC01Result = this.merchantService.merchantQueryReq(req);
		} catch (Exception e) {
			logger.error("MerchantQueryReqController ===> merchantService.merchantQueryReq,e:"+e);
			fftBARC01Result = FftResult.error();
		}
		logger.info("merchantQueryReq end,req:{}",fftBARC01Result.toString());
		return fftBARC01Result;
	}
}
