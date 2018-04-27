package com.gl365.order.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.remote.dto.ApproveCashReq;
import com.gl365.order.remote.dto.OrderInfoRsp;
import com.gl365.order.remote.dto.OrderQueryReq;
import com.gl365.order.remote.dto.PointsRewardReq;
import com.gl365.order.remote.dto.PointsRewardRsp;
import com.gl365.order.remote.dto.PrepayBoReq;
import com.gl365.order.remote.dto.QueryOrderDetail;
import com.gl365.order.remote.dto.RefundBoReq;
import com.gl365.order.remote.dto.RefundInfoBoReq;
import com.gl365.order.remote.dto.RefundInfoRsp;
import com.gl365.order.remote.dto.RefundRsp;
import com.gl365.order.remote.dto.ResultDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;
//@FeignClient(name = "wxpay-gateway",url = "http://192.168.0.198:8083")

//@FeignClient(name = "wxpay-gateway"
//,url = "http://192.168.0.62:18094")
@FeignClient(name = "wxpay-gateway")
public interface WxPayGatewayService {
	

	/**
	 * 审核提现
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/account/cash/approve", method = RequestMethod.POST)
	public ResultDto<?> cashApprove(@RequestBody ApproveCashReq req);
	
	
	/**
	 * 订单查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderQuery", method = RequestMethod.POST)
	public ResultDto<QueryOrderDetail> doAction(@RequestBody OrderQueryReq req);
	
	/**
	 * 创建订单
	 * @param req
	 * @return
	 */
    @RequestMapping(value = "/prePay", method = RequestMethod.POST)
    public ResultDto<OrderInfoRsp> doAction(PrepayBoReq req);
    
    /**
     * 退款单 查询融脉退款接口
     * @param req
     * @return
     */
	@RequestMapping(value = "/refundInfo", method = RequestMethod.POST)
	public ResultDto<RefundInfoRsp> doAction( RefundInfoBoReq req);
	/**
	 * 退款接口
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public ResultDto<RefundRsp> doAction(RefundBoReq req);
	
    @ApiOperation("乐豆打赏")
    @RequestMapping(value = "/pointsReward", method = RequestMethod.POST)
    public PointsRewardRsp pointsReward(@RequestBody PointsRewardReq req);
	

}
