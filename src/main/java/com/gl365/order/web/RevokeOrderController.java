package com.gl365.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.RevokeOrderCommand;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/rm")
public class RevokeOrderController {

	private static final Logger LOG = LoggerFactory.getLogger(RevokeOrderController.class);
	
	@Autowired
	private RefundOrderService refundOrderService; 
	/**
	 * c 端撤销群买单
	 * @param command
	 * @return
	 */
	@PostMapping("/c/group/revoke")
	@ApiOperation(value = "c端撤销群买单退单", httpMethod = "POST", response = ResultDto.class)
	public Object cGroupRevoke(@RequestBody RevokeOrderCommand command) {
		try {//模拟打赏和交易的订单。目前失败
			LOG.info("c端撤销群买单  RevokeOrderController. cGroupRevoke.command={}", JsonUtil.toJsonString(command));
			ResultDto<?> result = refundOrderService.updateRevokeByOrderSn(command);
			LOG.info("c端撤销群买单  RevokeOrderController. cGroupRevoke.result={}", JsonUtil.toJsonString(result));
				return result;
		} catch (Exception e) {
			LOG.error("c端撤销群买单 > > > 失败:{}",e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	@PostMapping("/update/refund/{orderSn}")
	@ApiOperation(value = "强制撤销订单", httpMethod = "POST", response = ResultDto.class)
	public Object updateRefundByOrderSn(@PathVariable("orderSn") String orderSn) {
		try {//模拟打赏和交易的订单。目前失败
			LOG.info("创建订单  OrderController. updateRefundByOrderSn.command={}", JsonUtil.toJsonString(orderSn));
			int i = refundOrderService.updateRefundByOrderSn(orderSn);
			LOG.info("创建订单  OrderController. updateRefundByOrderSn.result={}", JsonUtil.toJsonString(i));
			return new ResultDto <>(ResultCodeEnum.System.SUCCESS,i);
		} catch (Exception e) {
			LOG.error("创建订单 > > > 失败:{}", JsonUtil.toJsonString(orderSn),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

}
