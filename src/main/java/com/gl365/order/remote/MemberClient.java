package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.remote.dto.CancelOrderDto;

import io.swagger.annotations.ApiOperation;

@FeignClient(name = "member")
public interface MemberClient {

	/**
	 * 
	 * @param req
	 * @return
	 */
	
	@ApiOperation(value = "C端撤单")
	@RequestMapping(value = "/member/cancelorder/cancelorder", method = RequestMethod.POST)
	public ResultDto<?> cancelorder(@RequestBody CancelOrderDto command);
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	@ApiOperation(value = "C端撤单中")
	@RequestMapping(value = "/member/cancelorder/cancelordering", method = RequestMethod.POST)
	public ResultDto<?> cancelordering(@RequestBody CancelOrderDto command);

}
