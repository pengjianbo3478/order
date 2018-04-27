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
import com.gl365.order.dto.order.command.OrderRefundCommand;
import com.gl365.order.service.OrderConfirmService;
//import com.gl365.order.dto.req.gateway.NotifyCashResultsReq;
//import com.gl365.order.dto.req.gateway.NotifyCashWithdrawalReq;
//import com.gl365.order.remote.WxPayGatewayService;
//import com.gl365.order.remote.dto.ApproveCashReq;
//import com.gl365.order.service.CashOrderService;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/rm")
public class WxOrderController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WxOrderController.class);
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	@Autowired
	private OrderConfirmService orderConfirmService;
	
	@PostMapping("/refund")
	@ApiOperation(value = "退款单", httpMethod = "POST", response = ResultDto.class)
	public Object refund(@RequestBody OrderRefundCommand command){
		LOG.info("退款单入参===========》》{}",JsonUtil.toJsonString(command));
		try{
			ResultDto<?> result =refundOrderService.refundOrder(command);
			LOG.info("退款单返回===========》》{}",JsonUtil.toJsonString(result));
			return result;
		}catch(Exception e){
			//LOG.error("退款失败===========》》{}错误信息：{}", JsonUtil.toJsonString(command),e);
			LOG.error("退款失败===========》》{}错误信息{}",e ,JsonUtil.toJsonString(command));
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	
	@PostMapping("/confirm/{orderSn}")
	@ApiOperation(value = "抵扣乐豆", httpMethod = "POST", response = ResultDto.class)
	public Object refund(@PathVariable("orderSn") String orderSn){
		LOG.info("抵扣乐豆===========》》{}",JsonUtil.toJsonString(orderSn));
		try{
			ResultDto<?> result =orderConfirmService.confirm(orderSn);
			LOG.info("抵扣乐豆返回===========》》{}",JsonUtil.toJsonString(result));
			return result;
		}catch(Exception e){
			LOG.error("抵扣乐豆失败===========》》{}错误信息{}",e ,JsonUtil.toJsonString(orderSn));
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
}
