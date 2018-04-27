package com.gl365.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.PointsRewardReq;
import com.gl365.order.remote.dto.PointsRewardRsp;
import com.gl365.order.util.JsonUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/rm")
public class TipOrderController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TipOrderController.class);
	
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService; 
	/**
	 * 创建订单
	 * 
	 * @param command
	 * @return
	 */
	

	//TODO
	
    @PostMapping("pointsReward")
    @HystrixCommand(fallbackMethod = "fallback")
    @ApiOperation("乐豆打赏")
    public PointsRewardRsp pointsReward(@RequestBody PointsRewardReq req){
    	LOG.info("乐豆打赏 in {}",JsonUtil.toJsonString(req));
        PointsRewardRsp rsp =wxPayGatewayService.pointsReward(req);
        LOG.info("乐豆打赏 out {}",JsonUtil.toJsonString(rsp));
        if("01".equals(rsp.getPayStatus())){
            rsp.setRespCode(ResultCodeEnum.System.SUCCESS.getCode());
        }else{
            rsp.setRespCode(rsp.getPayStatus());
        }
        rsp.setRespMsg(rsp.getPayDesc());
        return rsp;
    }


    public PointsRewardRsp fallback( PointsRewardReq req){
        return null;
    }
}
