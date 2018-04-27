package com.gl365.order.service.repo;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.req.operator.GetOperatorListReq;
import com.gl365.order.dto.rsp.operator.MerchantOperatorDtoResult;

/**
 * < 操作员详情接口repo >
 * 
 * @since hui.li 2017年5月16日 上午10:53:39
 */
@FeignClient("merchant")
public interface OperatorInfoServiceRepo {

	/**
	 * 获取商家员工明细
	 * 
	 * @param operatorId
	 * @return
	 */
	@RequestMapping(value = "/merchant/operator/queryOperatorById", method = RequestMethod.GET)
	public ResultDto<MerchantOperatorDtoResult> queryOperator(@RequestParam("operatorId") String operatorId);

	/**
	 * 获取商家员工列表
	 * 
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "/merchant/operator/queryMerchantOpertator", method = RequestMethod.POST)
	public ResultDto<List<MerchantOperatorDtoResult>> getMerchantOpertatorList(@RequestBody GetOperatorListReq command);
}
