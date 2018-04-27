package com.gl365.order.fft.controller.pay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftBARP01;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftBARP01Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.PayService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通B扫C支付接口：BARP01 条码支付消费请求
 */
@RestController
public class BarCodePayReqController extends BaseController<FftBARP01>{
	
	private static final Logger logger = LoggerFactory.getLogger(BarCodePayReqController.class);
	
	@Autowired
	private PayService payService;
	
	@PostMapping(value = "barCodePayRequest")
	@Override
	@ApiOperation(value = "B扫C支付", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftBARP01Result.class)
	public FftResult doAction(@RequestBody FftBARP01 req) {
		logger.info("barCodePayRequest B扫C支付 begin,req:{}",req.toString());
		FftResult fftBARP01Result = null;
		try {
			fftBARP01Result = this.payService.barCodePayReq(req);
		} catch (Exception e) {
			logger.error("barCodePayRequest ===> payService.barCodePayReq exception,e:"+e);
			fftBARP01Result = FftResult.error();
		}
		logger.info("barCodePayRequest end,fftBARP01Result:{}",fftBARP01Result.toString());
		return fftBARP01Result;
	}
}
