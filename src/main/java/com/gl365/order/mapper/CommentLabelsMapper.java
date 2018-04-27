package com.gl365.order.mapper;

import java.util.List;

import com.gl365.order.model.comment.CommentLabels;

public interface CommentLabelsMapper {

    int insertSelective(CommentLabels record);
    
    List<CommentLabels> selectByCommentId(String commentId);
}