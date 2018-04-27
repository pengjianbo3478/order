package com.gl365.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.merchant.MerchantBasicsInfoDto;

//@FeignClient(url = "http://192.168.0.134:8081", name = "merchant")
@FeignClient("merchant")
public interface MerchantService {

	
	/**
	 * 获取商家基本详情
	 * 
	 * @param commands
	 * @return
	 */
	@RequestMapping(value = "/merchant/basics/detail", method = RequestMethod.GET)
	public ResultDto<MerchantBasicsInfoDto> getBasicsDetail(@RequestParam("merchantNo") String merchantNo);
}
