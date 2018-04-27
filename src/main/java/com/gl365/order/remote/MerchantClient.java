package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.remote.dto.CancelOrderDto;

import io.swagger.annotations.ApiOperation;

@FeignClient(name = "merchant")
public interface MerchantClient {

	/**
	 * 
	 * @param req
	 * @return
	 */
	
	@ApiOperation(value = "B端撤单")
	@RequestMapping(value = "/merchant/cancelorder/cancelorder", method = RequestMethod.POST)
	public ResultDto<?> cancelorder(@RequestBody CancelOrderDto command);
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	@ApiOperation(value = "B端撤单中")
	@RequestMapping(value = "/merchant/cancelorder/cancelordering", method = RequestMethod.POST)
	public ResultDto<?> cancelordering(@RequestBody CancelOrderDto command);

}
