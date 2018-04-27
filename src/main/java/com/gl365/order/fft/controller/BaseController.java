package com.gl365.order.fft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.dto.req.FftHead;
import com.gl365.order.dto.rsp.FftResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public abstract class BaseController<T extends FftHead> {
	protected static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	@HystrixCommand(fallbackMethod = "fallback")
	public abstract FftResult doAction(@RequestBody T req);
	
	public FftResult fallback(@RequestBody T t){
		log.info("=====>接口超时，触发熔断方法");
		FftResult result = new FftResult();
		result.setRespCode("999999");
		result.setRespMsg("time out");
		return result;
	}
}
