package com.gl365.order.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.enums.BeanRefundStatusEnum;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.dto.CancelResultReq;
import com.gl365.order.remote.dto.PaymentBaseResp;
import com.gl365.order.remote.dto.PaymentCancelReq;
import com.gl365.order.remote.dto.RefundRsp;
import com.gl365.order.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentClient paymentClient;
	
	@Override
	public ResultDto<?> beanRefund(RefundRsp req,String organCode) {
		logger.info("beanRefund begin,req:{},organCode:{}",req.toString(),organCode);
		PaymentCancelReq reqParam = buildPaymentCancelReq(req, organCode);
		logger.info("beanRefund buildPaymentCancelReq,req:{},organCode:{}",req.toString(),organCode);
		PaymentBaseResp resp = null;
		try{
			resp = paymentClient.cancel(reqParam);
		}catch(Exception e){
			logger.error("beanRefund ===>  paymentClient.cancel exception:",e);
		}
		if(resp != null){
			logger.info("beanRefund ===>  paymentClient.cancel,rlt:{}",resp.toString());
			if(ResultCodeEnum.System.SUCCESS.getCode().equals(resp.getResultCode())){
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
			}else{
				return new ResultDto<>(ResultCodeEnum.Payment.BEAN_REFUND_FAIL);
			}
		}else{
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public BeanRefundStatusEnum queryBeanRefundRlt(CancelResultReq req) {
		logger.info("queryBeanRefundRlt begin,req:{}",req.toString());
		PaymentBaseResp resp = null;
		try{
			resp = paymentClient.queryCancelResult(req);
		}catch(Exception e){
			logger.error("queryBeanRefundRlt ===>  paymentClient.queryCancelResult exception:",e);
			return BeanRefundStatusEnum.INIT;
		}
		if(ResultCodeEnum.System.SUCCESS.getCode().equals(resp.getResultCode())){
			return BeanRefundStatusEnum.SUCCESS;
		}else{
			return BeanRefundStatusEnum.FAIL;
		}
	}
	
	private PaymentCancelReq buildPaymentCancelReq(RefundRsp req,String organCode){
		PaymentCancelReq reqParam = new PaymentCancelReq();
		reqParam.setCashAmount(req.getRefundAmt());
		reqParam.setMerchantOrderNo(req.getMchRefundNo());
		reqParam.setOrganCode(organCode);
		reqParam.setOrganOrderNo(req.getCpRefundNo());
		reqParam.setOrganPayTime(LocalDateTime.now());
		reqParam.setOrigMerchantOrderNo(req.getMchOrderNo());
		reqParam.setOrigOrganOrderNo(req.getCpOrderNo());
		reqParam.setTerminal(req.getTerminal());
		reqParam.setOperator(req.getOperator());
		return reqParam;
	}

}
