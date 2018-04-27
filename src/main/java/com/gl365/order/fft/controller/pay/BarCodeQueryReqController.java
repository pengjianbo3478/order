package com.gl365.order.fft.controller.pay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftBARQ01;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftBARP01Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.PayService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通B扫C支付接口：BARP01 条码支付消费请求
 */
@RestController
public class BarCodeQueryReqController extends BaseController<FftBARQ01>{
	
	private static final Logger logger = LoggerFactory.getLogger(BarCodeQueryReqController.class);
	
	@Autowired
	private PayService payService;
	
	@PostMapping(value = "barPayQueryRequest")
	@Override
	@ApiOperation(value = "B扫C支付结果查询", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftBARP01Result.class)
	public FftResult doAction(@RequestBody FftBARQ01 req){
		logger.info("barPayQueryRequest B扫C支付结果查询  begin, req:{}",req.toString());
		FftResult fftBARQ01Result = null;;
		try {
			fftBARQ01Result = this.payService.barPayQueryReq(req);
		} catch (Exception e) {
			logger.error("barPayQueryRequest ===> payService.barPayQueryReq exception,e:"+e);
			fftBARQ01Result = FftResult.error();
		}
		logger.info("barPayQueryRequest end,fftBARQ01Result:{}",fftBARQ01Result.toString());
		return fftBARQ01Result;
	}
}
