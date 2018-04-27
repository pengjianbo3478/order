package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.remote.dto.PayQueryReq;
import com.gl365.order.remote.dto.PayQueryResp;

//@FeignClient(name = "payment", url = "http://192.168.0.77:5000")
@FeignClient(name = "payquery")
public interface PayQueryClient {
	
	@RequestMapping(value = "queryPayMainGroupPay", method = RequestMethod.POST)
	PayQueryResp getPayInfoByIdAndGroupId(@RequestBody PayQueryReq request);
	

}
