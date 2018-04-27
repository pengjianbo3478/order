package com.gl365.order.fft.controller.merchant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftMER04;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftMER03Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.MerchantService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通商户增加终端接口
 */
@RestController
public class MerchantAddTerminalReqController extends BaseController<FftMER04>{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantAddTerminalReqController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@PostMapping(value = "merchantAddTerminal")
	@Override
	@ApiOperation(value = "商户增加终端", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftMER03Result.class)
	public FftResult doAction(@RequestBody FftMER04 req) {
		logger.info("MerchantAddTerminalReqController 商户修改,req:{}",req.toString());
		FftResult fftBARC01Result = null;
		try {
			fftBARC01Result = this.merchantService.MerchantAddTerminalReq(req);
		} catch (Exception e) {
			logger.error("MerchantAddTerminalReqController ===> merchantService.MerchantAddTerminalReq,e:"+e);
			fftBARC01Result = FftResult.error();
		}
		logger.info("MerchantAddTerminalReq end,req:{}",fftBARC01Result.toString());
		return fftBARC01Result;
	}
}
