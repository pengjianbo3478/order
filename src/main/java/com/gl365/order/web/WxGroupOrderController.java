package com.gl365.order.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.CreateGroupOrderCommand;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.service.OrderGroupService;
import com.gl365.order.service.OrderService;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/rm")
public class WxGroupOrderController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WxGroupOrderController.class);
	
	
	@Autowired
	private OrderGroupService orderGroupService; 
	/**
	 * 创建订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/init/create")
	@ApiOperation(value = "创建群订单", httpMethod = "POST", response = ResultDto.class)
	public Object create(@RequestBody CreateGroupOrderCommand command) {
		try {//模拟打赏和交易的订单。目前失败
			LOG.info("创建群订单init  OrderController. create.command={}", JsonUtil.toJsonString(command));
			ResultDto<?> result = orderGroupService.createGroupOrder(command);
			LOG.info("创建群订单init  OrderController. create.result={}", JsonUtil.toJsonString(result));
				return result;
		} catch (Exception e) {
			String errMsg = e.getMessage();
			if (StringUtils.isNotBlank(errMsg) && errMsg.indexOf("PRIMARY") != -1) {
				LOG.error("创建群订单 > > > 失败:{}", "order_sn违反唯一约束");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, "order_sn已经存在");
			}
			
			LOG.error("创建群订单 > > > 失败:{}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 创建订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/pay/create")
	@ApiOperation(value = "创建群订单", httpMethod = "POST", response = ResultDto.class)
	public Object payCreate(@RequestBody CreateGroupOrderCommand command) {
		try {//模拟打赏和交易的订单。目前失败
			LOG.info("创建群订单pay  OrderController. create.command={}", JsonUtil.toJsonString(command));
			ResultDto<?> result = orderGroupService.createPayGroupOrder(command);
			LOG.info("创建群订单pay  OrderController. create.result={}", JsonUtil.toJsonString(result));
				return result;
		} catch (Exception e) {
			String errMsg = e.getMessage();
			if (StringUtils.isNotBlank(errMsg) && errMsg.indexOf("PRIMARY") != -1) {
				LOG.error("创建群订单pay > > > 失败:{}", "order_sn违反唯一约束");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, "order_sn已经存在");
			}
			
			LOG.error("创建群订单pay > > > 失败:{}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

}
