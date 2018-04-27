package com.gl365.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.build.OrderBuild;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.OrderDto;
import com.gl365.order.dto.order.OrderListSum;
import com.gl365.order.dto.order.command.QueryOrderCommand;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.Order;
import com.gl365.order.service.QueryOrderService;
import com.gl365.order.util.JsonUtil;

@Service
public class QueryOrderServiceImpl implements QueryOrderService {
	private static final Logger LOG = LoggerFactory.getLogger(QueryOrderServiceImpl.class);
	@Autowired
	private OrderMapper orderMapper;
	
	public PageDto<OrderDto> selectOrderList(QueryOrderCommand command, int pageNo, int pageSize){
		
		PageHelper.startPage(pageNo, pageSize);
		Order target = new Order();
		BeanUtils.copyProperties(command, target);
		List<Order> list = this.orderMapper.selectOrderList(target, command.getFromDate(), command.getToDate(),
				command.getTotalAmountStart(), command.getTotalAmountEnd());
		if(null==list||0==list.size()){
			return new PageDto<>();
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		Long total=pageInfo.getTotal();
		
		PageDto <OrderDto> pg=	new PageDto<OrderDto>();
		pg.setCurPage(pageNo);
		pg.setList(orderDtoList);
		pg.setPageSize(pageSize);
		pg.setTotalCount((Integer)total.intValue());
		pg.setTotalPage(pageInfo.getPages());
		
		return pg;
	}
	
	@Override
	public ResultDto<OrderListSum> selectOrderListSum(QueryOrderCommand command) {
		Order target = new Order();
		BeanUtils.copyProperties(command, target);
		OrderListSum  rlt = orderMapper.selectOrderListSum(target, command.getFromDate(), command.getToDate(),
				command.getTotalAmountStart(), command.getTotalAmountEnd());
		if(rlt == null){
			rlt = new OrderListSum();
		}
		if(null == rlt.getTotalAmountSum()){
			rlt.setTotalAmountSum(BigDecimal.valueOf(0));
		}
		if(null == rlt.getBeanAmountSum()){
			rlt.setBeanAmountSum(BigDecimal.valueOf(0));
		}
		if(null == rlt.getCashAmountSum()){
			rlt.setCashAmountSum(BigDecimal.valueOf(0));
		}
		if(null == rlt.getGiftAmountSum()){
			rlt.setGiftAmountSum(BigDecimal.valueOf(0));
		}
		if(null == rlt.getNoBenefitAmountSum()){
			rlt.setNoBenefitAmountSum(BigDecimal.valueOf(0));
		}
		return new ResultDto<>(ResultCodeEnum.System.SUCCESS, rlt);
	}
	
	public List<OrderDto> getByOrderListGroupId(String groupId){
		try{
			LOG.info("M端查询群买单 groupId{}",groupId);
			List<Order>  list =orderMapper.getByOrderListGroupId(groupId);
			if(null==list||0==list.size()){
				return new ArrayList<OrderDto>();
		}
		List<OrderDto> orderDtoList =list.stream().map(order->OrderBuild.buildOrderDto(order)).collect(Collectors.toList());
		LOG.info("M端查询群买单 orderDtoList{}",JsonUtil.toJsonString(orderDtoList));
		return orderDtoList;
		}catch(Exception e){
			LOG.error("M端查询群买单异常{}",e);
			throw new RuntimeException(e);
		}
	}

	
	
	
	
}
