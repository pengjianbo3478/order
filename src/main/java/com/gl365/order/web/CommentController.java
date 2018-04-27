package com.gl365.order.web;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.enums.CommentPersonalStatusEnum;
import com.gl365.order.common.utils.FallbackHandlerUtils;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.comment.UserCommentDto;
import com.gl365.order.dto.comment.command.GetCommentStatusCommand;
import com.gl365.order.dto.comment.command.GetCommentTempleteCommand;
import com.gl365.order.dto.comment.command.GetMerchantCommentsCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.GetScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.dto.comment.command.SaveCommentPersonal4MemberCommand;
import com.gl365.order.dto.comment.command.UpdateComment4MemberCommand;
import com.gl365.order.model.comment.Comment;
import com.gl365.order.model.comment.CommentPersonal;
import com.gl365.order.service.CommentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * < 商家评论Controller >
 * 
 * @since hui.li 2017年5月15日 下午1:49:18
 */
@RestController
@RequestMapping("/order/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	/**
	 * 获取评论模板
	 * 
	 * @param industry
	 * @return
	 */
	@PostMapping("/label/template")
	@HystrixCommand(fallbackMethod = "templeteFallback")
	public Object templete(@RequestBody GetCommentTempleteCommand command) {
		return commentService.getTemplete(command);
	}

	/**
	 * 获取员工综合评价分数
	 * 
	 * @param merchantNo
	 * @return
	 */
	@PostMapping("/getOperatorScore")
	@HystrixCommand(fallbackMethod = "getOperatorScoreFallback")
	public Object getOperatorScore(@RequestBody GetOperatorScoreCommand command) {
		return commentService.getOperatorScore(command);
	}
	
	/**
	 * 获取所有员工总评价条数
	 * 
	 * @param merchantNo
	 * @return
	 */
	@PostMapping("/getAllOperatorComment")
	@HystrixCommand(fallbackMethod = "getAllOperatorCommentFallback")
	public Object getAllOperatorComment(@RequestBody GetOperatorScoreCommand command) {
		return commentService.getAllOperatorComment(command);
	}

	/**
	 * 获取员工综合评价明细
	 * 
	 * @param getOperatorScoreDetailed4MerchantCommand
	 * @return
	 */
	@PostMapping("/getOperatorScoreDetailed")
	@HystrixCommand(fallbackMethod = "getOperatorScoreDetailedFallback")
	public Object getOperatorScoreDetailed(@RequestBody GetOperatorScoreDetailed4MerchantCommand getOperatorScoreDetailed4MerchantCommand) {
		return commentService.getOperatorScoreDetailed(getOperatorScoreDetailed4MerchantCommand);
	}

	/**
	 * 获取商家综合评价明细
	 * 
	 * @param getScoreDetailed4MerchantCommand
	 * @return
	 */
	@PostMapping("/getScoreDetailed")
	@HystrixCommand(fallbackMethod = "getScoreDetailedFallback")
	public Object getScoreDetailed(@RequestBody GetScoreDetailed4MerchantCommand getScoreDetailed4MerchantCommand) {
		return commentService.getScoreDetailed(getScoreDetailed4MerchantCommand);
	}

	/**
	 * 保存打赏评论
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/saveCommentPersonal")
	public Object saveCommentPersonal(@RequestBody SaveCommentPersonal4MemberCommand command) {
		command.setStatus(CommentPersonalStatusEnum.ING.getValue());
		logger.info("<====saveUpdateCommentPersonal====>保存打赏评论 param={}",JsonUtils.toJsonString(command));
		return commentService.saveUpdateCommentPersonal(command);
	}

	/**
	 * 保存初始评论
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/saveComment")
	@HystrixCommand(fallbackMethod = "saveDefaultCommentFallback")
	public Object saveDefaultComment(@RequestBody SaveComment4MemberCommand command) {
		return commentService.saveDefaultComment(command);
	}

	/**
	 * 更新评论信息
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/updateComment")
	@HystrixCommand(fallbackMethod = "updateCommentFallback")
	public Object updateComment(@RequestBody UpdateComment4MemberCommand command) {
		return commentService.updateComment(command);
	}

	/**
	 * 获取评论列表
	 * 
	 * @param command
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/getCommentList")
	@HystrixCommand(fallbackMethod = "getCommentListFallback")
	public Object getCommentList(@RequestBody GetMerchantCommentsCommand command) {
		return commentService.getCommentList(command);
	}
	
	/**
	 * 获取群买单评论
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/getCommentListForGroupPay")
	@HystrixCommand(fallbackMethod = "getCommentListForGroupPayFallback")
	public Object getCommentListForGroupPay(@RequestBody GetMerchantCommentsCommand command) {
		return commentService.getCommentListForGroupPay(command);
	}
	
	/**
	 * 获取评论状态
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/getCommentStatus")
	@HystrixCommand(fallbackMethod = "getCommentStatusFallback")
	public Object getCommentStatus(@RequestBody GetCommentStatusCommand req) {
		logger.info("getCommentStatus begin,req={}", JsonUtils.toJsonString(req));
		ResultDto<?> resp = null;
		if (null == req || StringUtils.isEmpty(req.getMerchantNo()) || StringUtils.isEmpty(req.getPaymentNo()) || StringUtils.isEmpty(req.getUserId())) {
			logger.error("getCommentStatus req's param is null");
			resp = new ResultDto<>(ResultCodeEnum.System.PARAM_NULL);
		} else {
			try {
				Comment comment = commentService.getCommentInfo(req.getUserId(), req.getMerchantNo(), req.getPaymentNo());
				if (comment == null) {
					resp = new ResultDto<Integer>(ResultCodeEnum.System.SUCCESS, null);
				} else {
					resp = new ResultDto<Integer>(ResultCodeEnum.System.SUCCESS, comment.getStatus());
				}
			} catch (Exception e) {
				logger.error("getCommentStatus ===> commentService.getCommentStatus exception,e:" + e);
				resp = ResultDto.getErrInfo();
			}
		}
		logger.info("getCommentStatus end,resp:{}", JsonUtils.toJsonString(resp));
		return resp;
	}

	/**
	 * 获取打赏状态
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/personalComment/status")
	@HystrixCommand(fallbackMethod = "personalCommentStatusFallback")
	public Object getPersonalCommentStatus(@RequestBody String req) {
		logger.info("getPersonalCommentStatus begin,req={}", req);
		if (StringUtils.isBlank(req)) {
			return null;
		}
		List<String> paymentNos = Arrays.asList(req.split(","));
		return commentService.getCommentPersonalStatus(paymentNos);
	}

	/**
	 * 我的评论查询
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/user/{userId}/{curPage}/{pageSize}")
	public ResultDto<PageDto<UserCommentDto>> queryMyComment(@PathVariable("userId") String userId, @PathVariable("curPage") String curPage, @PathVariable("pageSize") String pageSize) {
		return commentService.getUserComments(userId, curPage, pageSize);
	}
	
	@PostMapping("/updateCommentPersonalStatus")
	public Object updateCommentPersonalStatus(@RequestBody CommentPersonal command) {
		return commentService.updateCommentPersonalStatus(command);
	}

	public Object saveDefaultCommentFallback(@RequestBody SaveComment4MemberCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object updateCommentFallback(@RequestBody UpdateComment4MemberCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object getCommentListFallback(@RequestBody GetMerchantCommentsCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}
	
	public Object getCommentListForGroupPayFallback(@RequestBody GetMerchantCommentsCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object getCommentStatusFallback(@RequestBody GetCommentStatusCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object getScoreDetailedFallback(@RequestBody GetScoreDetailed4MerchantCommand getScoreDetailed4MerchantCommand) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object getOperatorScoreFallback(@RequestBody GetOperatorScoreCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}
	
	public Object getAllOperatorCommentFallback(@RequestBody GetOperatorScoreCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object getOperatorScoreDetailedFallback(@RequestBody GetOperatorScoreDetailed4MerchantCommand getOperatorScoreDetailed4MerchantCommand) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object personalCommentStatusFallback(@RequestBody String req) {
		return FallbackHandlerUtils.timeOutResult();
	}

	public Object templeteFallback(@RequestBody GetCommentTempleteCommand command) {
		return FallbackHandlerUtils.timeOutResult();
	}
}
