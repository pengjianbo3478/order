package com.gl365.order.handler;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl365.order.common.enums.CommentPersonalStatusEnum;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.dto.comment.command.SaveCommentPersonal4MemberCommand;
import com.gl365.order.dto.comment.command.UpdateComment4MemberCommand;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.dto.mq.payment.model.PayMain;
import com.gl365.order.dto.mq.payment.model.PayReturn;
import com.gl365.order.dto.mq.payment.model.PayStream;
import com.gl365.order.enums.PaymentTypeEnum;
import com.gl365.order.handler.impl.PaymentSucceeHandlerImpl;
import com.gl365.order.mq.producer.JPushProducer;
import com.gl365.order.mq.producer.MerchantCommentGradeProducer;
import com.gl365.order.service.CommentService;
import com.gl365.order.service.repo.OperatorInfoServiceRepo;

/**
 * < 交易通知基础处理 >
 * 
 * @since hui.li 2017年5月27日 上午10:22:47
 */
public abstract class AbstractPaymentHandler implements PaymentHandler {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractPaymentHandler.class);
	protected String tranType, preTranType/* 原订单交易类型 */, merchantNo, orderType, userId, splitFlag;

	protected String paymentNo, prePaymentNo, orderNo, preOrderNo, billNo;

	protected BigDecimal totalAmount; /* 交易金额 */

	protected PayMain payMain;/* 交易订单 */

	protected PayReturn payReturn;/* 退货订单 */

	protected PayStream payStream;/* 订单（撤销） */

	/* new instance by distribute */
	public JPushProducer jPushProducer;
	public CommentService commentService;
	public OperatorInfoServiceRepo operatorInfoServiceRepo;
	public MerchantCommentGradeProducer merchantCommentGradeProducer;

	// 核心字段构建
	@Override
	public PaymentHandler build(PaymentMQ command) {
		payMain = command.getPaymentBody().getPayMain();
		tranType = preTranType = command.getTranType(); // 当前订单交易类型，原订单交易类型默与当前订单一致
		orderType = payMain.getOrderType().trim();// 订单类型1：正常订单（默认为1）2：打赏订单3：达人订单4:网购订单
		merchantNo = payMain.getMerchantNo();// 商户编号
		userId = payMain.getUserId(); // 会员ID
		billNo = payMain.getRequestId(); // 用户订单号
		orderNo = payMain.getMerchantOrderNo(); // 给乐订单号
		preOrderNo = payMain.getRewardPayId();// 给乐原订单号,用于打赏支付场景
		paymentNo = payMain.getPayId(); // 当前支付订单
		totalAmount = payMain.getTotalAmount(); // 交易金额
		splitFlag = payMain.getSplitFlag(); ///*0:群买单主单, 1：群买单子单。群买单时不为空*/
		// 部分退货bug修改czw
		if (PaymentTypeEnum.containTH(tranType)) {
			payReturn = command.getPaymentBody().getPayReturn();
			preTranType = payMain.getTransType(); // 原订单交易类型,从原订单体中获取
			billNo = payReturn.getRequestId(); // 用户订单号
			paymentNo = payReturn.getPayId(); // 当前支付订单
			prePaymentNo = payReturn.getOrigPayId();// 原交易订单号
		}
		if (PaymentTypeEnum.containCX(tranType)) {
			payStream = command.getPaymentBody().getPayStream();
			preTranType = payMain.getTransType(); // 原订单交易类型,从原订单体中获取
			prePaymentNo = payMain.getPayId();// 原交易订单号取原订单信息
			billNo = payStream.getRequestId(); // 用户订单号
			paymentNo = payStream.getPayId(); // 当前支付订单
		}
		return this;
	}

	// push
	@Override
	@Deprecated
	public void push() {
		return;
	}

	// 保存商家评论
	protected void saveMerchantComment() {
		SaveComment4MemberCommand command = new SaveComment4MemberCommand(userId, merchantNo, paymentNo, totalAmount);
		commentService.saveDefaultComment(command);
	}
	
	// 保存群买单评论
	protected void saveGroupPayComment() {
		// 判断是不是主单,主单才写评论
		if("0".equals(splitFlag)){
			SaveComment4MemberCommand command = new SaveComment4MemberCommand(userId, merchantNo, paymentNo, totalAmount);
			commentService.saveDefaultComment(command);
		}
	}

	// 保存打赏评论
	protected void savePersonalComment() {
		LOG.info("AbstractPaymentHandler savePersonalComment: start");
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand();
		command.setUserId(userId);
		command.setMerchantNo(merchantNo);
		command.setPaymentNo(orderNo);
		command.setStatus(CommentPersonalStatusEnum.NONE.getValue());
		LOG.info("<====saveUpdateCommentPersonal====>保存打赏评论 AbstractPaymentHandler saveUpdateCommentPersonal: {}",JsonUtils.toJsonString(command));
		commentService.saveUpdateCommentPersonal(command);
	}

	// 回滚打赏评论
	protected void backPersonalComment() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, orderNo);
		command.setPaymentNo(preOrderNo);// 原支付订单号
		command.setStatus(CommentPersonalStatusEnum.ING.getValue());// 打赏中
		LOG.info("<====saveUpdateCommentPersonal====>回滚打赏评论 param={}",JsonUtils.toJsonString(command));
		commentService.saveUpdateCommentPersonal(command);
	}

	// 完成打赏评论
	protected void donePersonalComment() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, orderNo);
		command.setPaymentNo(preOrderNo);// 原支付订单号
		command.setStatus(CommentPersonalStatusEnum.DONE.getValue());// 打赏成功
		LOG.info("<====saveUpdateCommentPersonal====>完成打赏评论 param={}",JsonUtils.toJsonString(command));
		commentService.saveUpdateCommentPersonal(command);
	}

	// 删除打赏评论
	protected void removePersonalComment() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, orderNo);
		command.setPaymentNo(preOrderNo);// 原支付订单号
		command.setDelete(true);
		LOG.info("<====saveUpdateCommentPersonal====>删除打赏评论 param={}",JsonUtils.toJsonString(command));
		commentService.saveUpdateCommentPersonal(command);
	}

	// 还原打赏评论
	protected void unRemovePersonalComment() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, orderNo);
		command.setPaymentNo(preOrderNo);// 原支付订单号
		command.setDelete(false);
		commentService.saveUpdateCommentPersonal(command);
	}

	// 回滚商家评论
	protected void backMerchantComment() {
		UpdateComment4MemberCommand command = new UpdateComment4MemberCommand(userId, merchantNo, prePaymentNo);
		command.setDelete(true);
		commentService.backRecoverComment(command);
	}

	// 恢复商家评论
	protected void recoverMerchantComment() {
		UpdateComment4MemberCommand command = new UpdateComment4MemberCommand(userId, merchantNo, prePaymentNo);
		command.setDelete(false);
		commentService.backRecoverComment(command);
	}

}
