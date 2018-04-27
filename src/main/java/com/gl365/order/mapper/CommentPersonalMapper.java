package com.gl365.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gl365.order.dto.comment.PersonalCommentStatus;
import com.gl365.order.model.comment.CommentGradeLeave;
import com.gl365.order.model.comment.CommentPersonal;

public interface CommentPersonalMapper {
	Integer deleteByPrimaryKey(String id);

	/**
	 * 保存打赏订单
	 * 
	 * @param record
	 * @return
	 */
	Integer insertSelective(CommentPersonal record);

	CommentPersonal selectByPrimaryKey(String id);

	Integer updateByPrimaryKeySelective(CommentPersonal record);

	/**
	 * 统计操作员评价分数
	 * 
	 * @param merchantNo
	 * @return
	 */
	List<CommentPersonal> selectOperatorScoreByMerchantNoOperatorId(@Param("merchantNo") String merchantNo, @Param("operatorId") String operatorId);

	/**
	 * 评分分页展示
	 * 
	 * @param param
	 * @return
	 */
	Integer selectCommentPersonalCount(Map<String, Object> param);

	List<CommentPersonal> selectCommentPersonalPage(Map<String, Object> param);

	/**
	 * 统计员工各评分等级
	 * 
	 * @param param
	 * @return
	 */
	List<CommentGradeLeave> selectGradeLeaveByMerchantNo(Map<String, Object> param);

	List<PersonalCommentStatus> queryCommentStatus(@Param("paymentNos") List<String> paymentNos);

	/**
	 * 查询打赏订单
	 * 
	 * @param userId
	 * @param merchantNo
	 * @return
	 */
	List<CommentPersonal> selectByUidMnoPno(@Param("userId") String userId, @Param("paymentNo") String paymentNo);

	/**
	 * 更新打赏订单
	 * 
	 * @param buildUpdateCommentPersonal
	 * @return
	 */
	Integer updateByUidMnoPno(CommentPersonal buildUpdateCommentPersonal);
	
	Integer updatePersonalCommentStatus(CommentPersonal commentPersonal);
	
	/**
	 * 统计本店员工所有评价数
	 * 
	 * @param merchantNo
	 * @return
	 */
	Integer selectOperatorCommentCountByMerchantNo(@Param("merchantNo") String merchantNo);
	
}