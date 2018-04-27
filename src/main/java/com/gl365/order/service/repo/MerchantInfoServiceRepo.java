package com.gl365.order.service.repo;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.req.merchant.GetMerchantDetail4MerchantReq;
import com.gl365.order.dto.req.merchant.UpdateMerchantCommentCountReq;
import com.gl365.order.dto.rsp.merchant.MerchantInfoDtoResult;
import com.gl365.order.dto.rsp.operator.MerchantOperatorDtoResult;

/**
 * < 商家详细信息接口消费 >
 */
@FeignClient("merchant")
public interface MerchantInfoServiceRepo {

	/**
	 * 获取店长信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/merchantAPI/operator/queryOperatorAdmin/{merchantNo}", method = RequestMethod.GET)
	ResultDto<MerchantOperatorDtoResult> queryOperatorAdmin(@PathVariable("merchantNo") String merchantNo);

	/**
	 * 累加商家评论统计信息
	 * 
	 * @param负数表示扣减
	 * @return
	 */
	@RequestMapping(value = "/merchant/comment/overCount", method = RequestMethod.POST)
	ResultDto<Integer> overMerchantCount(@RequestBody UpdateMerchantCommentCountReq command);

	/**
	 * 获取商家详情,支持批量
	 * 
	 * @param command2Merchant
	 * @return
	 */
	@RequestMapping(value = "/merchant/detail", method = RequestMethod.POST)
	ResultDto<List<MerchantInfoDtoResult>> getMerchantDetail(GetMerchantDetail4MerchantReq command2Merchant);
}
