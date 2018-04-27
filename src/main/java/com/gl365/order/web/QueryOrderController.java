package com.gl365.order.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.OrderListSum;
import com.gl365.order.dto.order.command.QueryOrderCommand;
import com.gl365.order.service.QueryOrderService;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/")
public class QueryOrderController {

	private static final Logger LOG = LoggerFactory.getLogger(QueryOrderController.class);
	
	
	@Autowired
	private QueryOrderService queryOrderService;

	
	
	
	
	/**
	 * 按用户id查询群买单主订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/selectOrderList/{pageNo}/{pageSize}")
	@ApiOperation(value = "用户id查询订单", httpMethod = "POST")
	public Object selectOrderList(@RequestBody QueryOrderCommand command,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize) {
	
		LOG.info("getByOrderMainMemberId 入参command{}pageNo {}pageSize {}", JsonUtil.toJsonString(command),pageNo,pageSize);
		try {
			PageDto<OrderDto> result =queryOrderService.selectOrderList(command, pageNo, pageSize);
				return result;
		} catch (Exception e) {
			LOG.error("按单用户查询群买主单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 按用户id查询群买单主订单合计
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/selectOrderListSum")
	@ApiOperation(value = "用户id查询订单合计", httpMethod = "POST")
	public ResultDto<OrderListSum> selectOrderListSum(@RequestBody QueryOrderCommand command) {
		LOG.info("selectOrderListSum begin param={}", JsonUtil.toJsonString(command));
		Long beginTime = System.currentTimeMillis();
		ResultDto<OrderListSum> rlt = null;
		try {
			rlt = queryOrderService.selectOrderListSum(command);
		} catch (Exception e) {
			LOG.error("selectOrderListSum exception,e:{}",e);
			rlt = new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("selectOrderListSum end rlt={},time={}ms",JsonUtil.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
	
	
	/**
	 * 按用户id查询群买单主订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/select/group/{groupId}")
	@ApiOperation(value = "用户id查询订单", httpMethod = "POST")
	public Object selectGroupOrderList(@PathVariable("groupId") String groupId) {
	
		LOG.info("selectGroupOrderList 入参groupId{}", JsonUtil.toJsonString(groupId));
		try {
			 List<OrderDto> result= queryOrderService.getByOrderListGroupId(groupId);
				return result;
		} catch (Exception e) {
			LOG.error("按单用户查询群买主单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(groupId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

}
