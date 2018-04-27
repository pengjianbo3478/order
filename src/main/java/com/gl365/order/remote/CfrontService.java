package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.remote.dto.GroupPayDistributeReq;

@FeignClient(name="cfront",url="${${env:}.url.cfront:}")
public interface CfrontService {

	/**
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/lifeAPI/turnTable/updateUserPayStatus", method = RequestMethod.POST)
	ResultDto<?> cancel(@RequestBody GroupPayDistributeReq req);

}
