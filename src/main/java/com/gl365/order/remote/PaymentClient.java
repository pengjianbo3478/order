package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.remote.dto.CancelResultReq;
import com.gl365.order.remote.dto.PaymentBaseResp;
import com.gl365.order.remote.dto.PaymentCancelReq;
import com.gl365.order.remote.dto.WxConfirmReqDTO;
import com.gl365.order.remote.dto.WxConfirmRespDTO;

//@FeignClient(name = "payment"
//,url = "http://192.168.0.62:18089")
@FeignClient(name = "payment")
public interface PaymentClient {

	/**
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/wx/cancel", method = RequestMethod.POST)
	PaymentBaseResp cancel(@RequestBody PaymentCancelReq req);

	
	/**
	 * 交易扣豆
	 * @param wxConfirmReqDTO
	 * @return
	 */
	@RequestMapping(value = "wx/confirmpay", method = RequestMethod.POST)
	public WxConfirmRespDTO confirm(@RequestBody WxConfirmReqDTO wxConfirmReqDTO) ;

	/**
	 * 退款扣豆查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/wx/cancelResultQuery", method = RequestMethod.POST)
	PaymentBaseResp queryCancelResult(@RequestBody CancelResultReq req);

}
