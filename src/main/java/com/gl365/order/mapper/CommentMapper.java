package com.gl365.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gl365.order.model.comment.Comment;
import com.gl365.order.model.comment.CommentGradeLeave;

@Repository
public interface CommentMapper {
	int deleteByPrimaryKey(String id);

	int insertSelective(Comment record);

	Comment selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Comment record);

	/*
	 * 根据useid查评论
	 */
	List<Comment> selectByUserId(Map<String, Object> map);

	int selectByUserIdCount(Map<String, Object> map);

	/*
	 * 根据商家编号查询评论
	 */
	List<Comment> selectByMerchantNo(Map<String, Object> map);

	int selectCountByMerchantNo(Map<String, Object> map);

	/**
	 * 更新评论
	 * 
	 * @param buildUpdateComment
	 */
	void updateWithUidMnoPno(Comment buildUpdateComment);
	
	Comment getCommentStatus(@Param("userId") String userId, @Param("merchantNo") String merchantNo, @Param("paymentNo") String paymentNo);

	Comment selectByUidMnoPno(@Param("userId") String userId, @Param("merchantNo") String merchantNo, @Param("paymentNo") String paymentNo);

	Integer selectCommentStatus(@Param("userId") String userId, @Param("merchantNo") String merchantNo, @Param("paymentNo") String paymentNo);

	/**
	 * 统计商家评分等级
	 * 
	 * @param param
	 * @return
	 */
	List<CommentGradeLeave> selectGradeLeaveByMerchantNo(Map<String, Object> param);

}