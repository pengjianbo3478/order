package com.gl365.order.event;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.gl365.order.common.enums.ApprovalStatus;
import com.gl365.order.dto.req.gateway.NotifyCashWithdrawalReq;
import com.gl365.order.mapper.OrderCashMapper;
import com.gl365.order.model.OrderCash;
import com.gl365.order.remote.WxPayGatewayService;
import com.gl365.order.remote.dto.ApproveCashReq;
import com.gl365.order.util.GsonUtils;



/**
 * @author Chen
 */
@Component
public class AuditCashEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuditCashEventHandler.class);
	
	@Autowired
	private WxPayGatewayService wxPayGatewayService;
	
	@Autowired
	private OrderCashMapper orderCashMapper;
	
	@Async
	@TransactionalEventListener(value = AuditCashEvent.class, phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
	@Transactional
	public void syncMerchant(AuditCashEvent event) {
		logger.info("收到提现申请的消息：{} 准备审核", event.getCashJson());
		//json转换为javaBean 准备处理
		NotifyCashWithdrawalReq cashWithdrawal=GsonUtils.fromJson2Object(event.getCashJson(),NotifyCashWithdrawalReq.class);
		
		ApproveCashReq req =new ApproveCashReq();
		BeanUtils.copyProperties(cashWithdrawal, req);
		req.setPono(getPono());
		req.setApprovalStatus(ApprovalStatus.pass.getValue());
		logger.info("提现申请消息转换完成：{} 准备发送审核结果", req);
		wxPayGatewayService.cashApprove(req);
		logger.info("提现申请消息发送审核结果完成：{}", req);
		
		OrderCash record =new OrderCash ();
		BeanUtils.copyProperties(req, record);
		record.setStatus(Integer.parseInt(ApprovalStatus.pass.getValue()));
		logger.info("提现申请消息准备修改审核状态：{}", record);
		int update=orderCashMapper.updateByApplyNo(record);
		logger.info("提现申请消息修改审核状态完成：{} 影响结果{}", record,update);
	}
	
	private String getPono(){
		String s=UUID.randomUUID().toString().replace("-", "");
		return s.substring(0, 10);
	}
	
}
