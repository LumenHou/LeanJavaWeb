package com.lean.lumen.service;

import com.lean.lumen.exception.CustomizeException;
import com.lean.lumen.mapper.CommentMapper;
import com.lean.lumen.mapper.QuestionMapper;
import com.lean.lumen.model.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    QuestionMapper questionMapper;

    public void createComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(2001, "问题没有父ID");
        }
        if (comment.getType().equals(1L)) {
            commentMapper.insert(comment);
            questionMapper.updateQuestionCommentCountById(comment.getParentId().intValue());
        } else {
            commentMapper.insert(comment);
        }
    }
}
