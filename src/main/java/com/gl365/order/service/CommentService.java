package com.gl365.order.service;

import java.util.List;

import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.PageResultDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.comment.CommentDto;
import com.gl365.order.dto.comment.CommentPersonalDto;
import com.gl365.order.dto.comment.PersonalCommentStatus;
import com.gl365.order.dto.comment.UserCommentDto;
import com.gl365.order.dto.comment.command.GetCommentTempleteCommand;
import com.gl365.order.dto.comment.command.GetMerchantCommentsCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.GetScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.dto.comment.command.SaveCommentPersonal4MemberCommand;
import com.gl365.order.dto.comment.command.UpdateComment4MemberCommand;
import com.gl365.order.model.comment.Comment;
import com.gl365.order.model.comment.CommentLabelsTemplate;
import com.gl365.order.model.comment.CommentPersonal;

/**
 * < 商家评论Service >
 * 
 * @author hui.li 2017年4月20日 - 下午3:00:42
 * @Since 1.0
 */
public interface CommentService {

	/**
	 * 获取商家评论模板
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<List<CommentLabelsTemplate>> getTemplete(GetCommentTempleteCommand command);

	/**
	 * 添加商户评论
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<?> saveDefaultComment(SaveComment4MemberCommand command);

	/**
	 * 更新评论信息
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<?> updateComment(UpdateComment4MemberCommand command);

	/**
	 * 撤销/恢复评论信息
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<?> backRecoverComment(UpdateComment4MemberCommand command);

	/**
	 * 获取商家评论列表
	 * 
	 * @param command
	 * @param pageSize
	 * @param curPage
	 * @return
	 */
	PageResultDto<CommentDto> getCommentList(GetMerchantCommentsCommand command);

	/**
	 * 获取商家群买单评论
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<CommentDto> getCommentListForGroupPay(GetMerchantCommentsCommand command);
	
	/**
	 * 获取商家综合评论明细
	 * 
	 * @param getScoreDetailed4MerchantCommand
	 * @return
	 */
	PageResultDto<CommentDto> getScoreDetailed(GetScoreDetailed4MerchantCommand getScoreDetailed4MerchantCommand);

	/**
	 * 获取员工综合评价分数
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<List<CommentPersonalDto>> getOperatorScore(GetOperatorScoreCommand command);
	
	/**
	 * 获取所有员工总评价条数
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<Integer> getAllOperatorComment(GetOperatorScoreCommand command);

	/**
	 * 获取员工综合评价明细
	 * 
	 * @param getOperatorScoreDetailed4MerchantCommand
	 * @return
	 */
	PageResultDto<CommentPersonalDto> getOperatorScoreDetailed(GetOperatorScoreDetailed4MerchantCommand getOperatorScoreDetailed4MerchantCommand);

	/**
	 * 获取打赏评论状态
	 * 
	 * @param paymentNos
	 * @return
	 */
	List<PersonalCommentStatus> getCommentPersonalStatus(List<String> paymentNos);

	/**
	 * 保存打赏评论
	 * 
	 * @param command
	 * @return
	 */
	ResultDto<Integer> saveUpdateCommentPersonal(SaveCommentPersonal4MemberCommand command);

	/**
	 * 获取评论明细
	 * 
	 * @param userId
	 * @param merchantNo
	 * @param paymentNo
	 * @return
	 */
	Comment getCommentInfo(String userId, String merchantNo, String paymentNo);

	/**
	 * 获取用户评论
	 * 
	 * @param userId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	ResultDto<PageDto<UserCommentDto>> getUserComments(String userId, String curPage, String pageSize);


	/**
	 * 更新打赏评论状态
	 * @param commentPersonal
	 * @return
	 */
	ResultDto<?> updateCommentPersonalStatus(CommentPersonal commentPersonal);
}
