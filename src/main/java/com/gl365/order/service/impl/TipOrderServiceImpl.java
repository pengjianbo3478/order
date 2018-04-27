package com.gl365.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.order.command.CreateOrderCommand;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.PointsRewardReq;
import com.gl365.order.remote.dto.PointsRewardRsp;
import com.gl365.order.service.OrderService;
import com.gl365.order.service.RefundOrderService;
import com.gl365.order.service.TipOrderService;
import com.gl365.order.util.JsonUtil;
import com.gl365.order.util.ReflectUtils;

import io.swagger.annotations.ApiOperation;

@Service
public class TipOrderServiceImpl implements TipOrderService{

	private static final Logger LOG = LoggerFactory.getLogger(TipOrderServiceImpl.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService; 

	
	  public ResultDto pointsReward(CreateOrderCommand command){
		  
			try {
				checkStrLength(command, "paymentConfig", true, 2,null);
				checkStrLength(command, "memberId", true, 35,null);
				checkStrLength(command, "merchantNo", true, 35,8);
			} catch (Exception e1) {
				LOG.info("新增订单异常参数校验不通过：exception:{}", e1);
				return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
			}
	    	PointsRewardReq req = createOrder( command);
	    	PointsRewardRsp rsp= pointsReward(req);
	    	 if(ResultCodeEnum.System.SUCCESS.getCode().equals(rsp.getRespCode())){
	    		 LOG.info("乐豆打赏 正常返回 {}",JsonUtil.toJsonString(rsp));
	    		return  new ResultDto(ResultCodeEnum.System.SUCCESS, command);
	    	}else{
	    		 LOG.info("乐豆打赏 异常返回 {}",JsonUtil.toJsonString(rsp));
	    		return new ResultDto<>(rsp.getRespCode(),rsp.getPayDesc(),rsp);
	    	}
	  }

	

    @ApiOperation("乐豆打赏")
    public PointsRewardRsp pointsReward(@RequestBody PointsRewardReq req){
    	LOG.info("乐豆打赏 in {}",JsonUtil.toJsonString(req));
        PointsRewardRsp rsp =wxPayGatewayService.pointsReward(req);
        LOG.info("乐豆打赏 out {}",JsonUtil.toJsonString(rsp));
        return rsp;
    }
    
    
  
    
    

    
    public PointsRewardReq createOrder(CreateOrderCommand command){
    	PointsRewardReq req=new PointsRewardReq();
    	req.setOrderType("5");
    	String orderSn=orderService.getOrderSn(command.getPaymentConfig(), command.getMerchantNo());
    	req.setMerchantOrderNo(orderSn);
    	req.setPayUserId(command.getMemberId());
    	req.setTotalAmount(command.getTotalAmount());
    	req.setRewardUserId(command.getRewardUserId());
    	req.setRewardPayId(command.getOrigOrderSn());
    	return req;
    }

    
	/**
	 * 检查对象字符属性不为空且未超长
	 * 
	 * @param obj
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param mustNeed
	 *            是否必须 true必须 false非必须
	 * @param maxLength
	 *            最大字符长度
	 * @return 返回null表示检查通过，否则返回检查失败的明细描述
	 * @throws Exception
	 */
	private void checkStrLength(Object obj, String fieldName, boolean mustNeed, int maxLength,Integer minLength) throws Exception {
		Object val = ReflectUtils.getValueByFieldName(obj, fieldName);
		if (val == null && mustNeed) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		
		if (val == null && !mustNeed) {
			return;
		}
		
		String str = String.valueOf(val);
		if (!str.trim().equals(str)) {
			str = str.trim();
			ReflectUtils.setValueByFieldName(obj, fieldName, str);
		}
		if (mustNeed && str.length() == 0) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		if (mustNeed && str.length() > maxLength) {
			throw new Exception("参数" + fieldName + "值不允许大于" + maxLength + "个字符");
		}
		if(null!=minLength){
			if (mustNeed && str.length() < minLength.intValue()) {
				throw new Exception("参数" + fieldName + "值不允许小于" + minLength + "个字符");
			}
		}
	}

}
