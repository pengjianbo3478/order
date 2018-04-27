package com.gl365.order.fft.controller.pay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.customer.FftBARC01;
import com.gl365.order.dto.rsp.FftResult;
import com.gl365.order.dto.rsp.customer.FftBARP01Result;
import com.gl365.order.fft.controller.BaseController;
import com.gl365.order.fft.service.PayService;

import io.swagger.annotations.ApiOperation;



/**
 * 付费通B扫C支付接口：BARP01 条码支付消费请求
 */
@RestController
public class BarCodePayCancelReqController extends BaseController<FftBARC01>{
	
	private static final Logger logger = LoggerFactory.getLogger(BarCodePayCancelReqController.class);
	
	@Autowired
	private PayService payService;
	
	@PostMapping(value = "barPayCancelRequest")
	@Override
	@ApiOperation(value = "B扫C支付撤销", httpMethod = "POST", notes="传递复杂对象信息，json格式传递数据", response = FftBARP01Result.class)
	public FftResult doAction(@RequestBody FftBARC01 req) {
		logger.info("barPayCancelRequest B扫C支付撤销 begin,req:{}",req.toString());
		FftResult fftBARC01Result = null;
		try {
			fftBARC01Result = this.payService.barPayCancelReq(req);
		} catch (Exception e) {
			logger.error("barPayCancelRequest ===> payService.barPayCancelReq exception,e:"+e);
			fftBARC01Result = FftResult.error();
		}
		logger.info("barPayCancelRequest end,req:{}",fftBARC01Result.toString());
		return fftBARC01Result;
	}
}
