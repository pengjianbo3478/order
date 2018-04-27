package com.gl365.order.web;

import org.apache.commons.lang.StringUtils;
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
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.dto.order.command.GetOrderSn4OrderCommand;
import com.gl365.order.dto.order.command.UpdateOrderCommand;
import com.gl365.order.enums.PaymentConfigEnum;
import com.gl365.order.service.OrderService;
import com.gl365.order.service.TipOrderService;
import com.gl365.order.util.JsonUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/rm")
public class OrderController {

	private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService; 
	@Autowired
	private TipOrderService tipOrderService;
	/**
	 * 创建订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/create")
	@ApiOperation(value = "创建订单", httpMethod = "POST", response = ResultDto.class)
	public Object create(@RequestBody CreateOrderCommand command) {
		try {//模拟打赏和交易的订单。目前失败
			LOG.info("创建订单  OrderController. create.command={}", JsonUtil.toJsonString(command));
			
			if(PaymentConfigEnum.tip_bean.getValue()!=command.getPaymentConfig().intValue()){
				ResultDto<?> result = orderService.createOrder(command);
				LOG.info("创建订单  OrderController. create.result={}", JsonUtil.toJsonString(result));
				return result;
			}else if(PaymentConfigEnum.tip_bean.getValue()==command.getPaymentConfig().intValue()){//纯乐豆打赏订单
				ResultDto<?> result =tipOrderService.pointsReward(command);
				LOG.info("创建纯乐豆打赏订单  OrderController. create.result={}", JsonUtil.toJsonString(result));
				return result;
			}
		
			return new ResultDto();
		} catch (Exception e) {
			String errMsg = e.getMessage();
			if (StringUtils.isNotBlank(errMsg) && errMsg.indexOf("PRIMARY") != -1) {
				LOG.error("创建订单 > > > 失败:{}", "order_sn违反唯一约束");
				return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, "order_sn已经存在");
			}
			
			LOG.error("创建订单 > > > 失败:{}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 修改订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改订单", httpMethod = "POST", response = ResultDto.class)
	public Object create(@RequestBody UpdateOrderCommand command) {
		try {
			ResultDto<?> result = orderService.updateOrder(command);
				return result;

		} catch (Exception e) {
			LOG.error("修改订单 > > > 失败:{}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 按单号查询订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderBySn")
	@ApiOperation(value = "按单号查询订单", httpMethod = "POST", response = ResultDto.class)
	public Object create(@RequestBody GetOrderSn4OrderCommand command) {
		try {
			ResultDto<OrderDto> result = orderService.getOrderBySn(command);
				return result;
		} catch (Exception e) {
			LOG.error("按单号查询订单 > > > 失败:{}", JsonUtil.toJsonString(command),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	
	/**
	 * 按单号查询订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderMainBySn/{orderSn}")
	@ApiOperation(value = "按单号查询订单", httpMethod = "POST", response = ResultDto.class)
	public Object getOrderMainBySn(@PathVariable("orderSn") String orderSn) {
		try {
			ResultDto<OrderDto> result = orderService.getOrderMainBySn(orderSn);
				return result;
		} catch (Exception e) {
			LOG.error("按单号查询订单 > > > 失败:{}", JsonUtil.toJsonString(orderSn),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	
	
	/**
	 * 按用户id查询订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderByMember/{memberId}")
	@ApiOperation(value = "用户id查询订单", httpMethod = "POST")
	public Object getOrderByMember(@PathVariable("memberId") String memberId) {
		LOG.info("用户查询未完成群买单 > > > :{}", memberId);
		try {
			ResultDto<OrderDto> result = orderService.getOrderByMemberId(memberId);
			LOG.info("用户查询未完成群买单 return > > > :{}", JsonUtil.toJsonString(result));
				return result;
		} catch (Exception e) {
			LOG.error("按单号查询订单 > > > 失败:{}", JsonUtil.toJsonString(memberId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	
	/**
	 * 按用户id查询群买单主订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderMainByMember/{memberId}/{pageNo}/{pageSize}")
	@ApiOperation(value = "用户id查询订单", httpMethod = "POST")
	public Object getByOrderMainMemberId(@PathVariable("memberId") String memberId,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize) {
	
		LOG.info("群买单主单入参memberId{}pageNo {}pageSize {}",memberId,pageNo,pageSize);
		try {
			PageDto<OrderDto> result = orderService.getByOrderMainMemberId(memberId, pageNo, pageSize);
				return result;
		} catch (Exception e) {
			LOG.error("按单用户查询群买主单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(memberId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 按用户id查询群买单子订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderSonByMember/{memberId}/{pageNo}/{pageSize}")
	@ApiOperation(value = "用户id查询订单", httpMethod = "POST")
	public Object getByOrderSonMemberId(@PathVariable("memberId") String memberId,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize) {
	
		LOG.info("群买单子单入参memberId{}pageNo {}pageSize {}",memberId,pageNo,pageSize);
		try {
			PageDto<OrderDto> result = orderService.getByOrderSonMemberId(memberId, pageNo, pageSize);
				return result;
		} catch (Exception e) {
			LOG.error("按单用户查询群买子单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(memberId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	
	/**
	 * 按用户id查询群买单子订单
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getOrderByGroup/{groupId}/{pageNo}/{pageSize}")
	@ApiOperation(value = "群id查询订单", httpMethod = "POST")
	public Object getByOrderGroupId(@PathVariable("groupId") String groupId,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize) {
	
		LOG.info("群买单入参memberId{}pageNo {}pageSize {}",groupId,pageNo,pageSize);
		try {
			PageDto<OrderDto> result = orderService.getByOrderGroupId(groupId, pageNo, pageSize);
				return result;
		} catch (Exception e) {
			LOG.error("按单分组单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(groupId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	@PostMapping("/getByOrderGroupIdCancelOrder/{groupId}/{orderStatus}/{pageNo}/{pageSize}")
	@ApiOperation(value = "根据状态和群id查询订单", httpMethod = "POST")
	public Object getByOrderGroupIdCancelOrder(@PathVariable("groupId") String groupId,@PathVariable("orderStatus") String orderStatus,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize) {
		
		LOG.info("群买单入参memberId{} orderStatus{} pageNo {}pageSize {}",groupId,orderStatus,pageNo,pageSize);
		try {
			PageDto<OrderDto> result = orderService.getByOrderGroupIdCancelOrder(groupId, orderStatus, pageNo, pageSize);
			return result;
		} catch (Exception e) {
			LOG.error("按单分组单询订单 > > > 失败:{} {}", JsonUtil.toJsonString(groupId),e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	


}
