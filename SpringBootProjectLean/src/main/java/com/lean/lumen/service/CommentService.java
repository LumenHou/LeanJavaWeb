package com.lean.lumen.service;

import com.lean.lumen.dto.CommentDTO;
import com.lean.lumen.exception.CustomizeException;
import com.lean.lumen.mapper.CommentMapper;
import com.lean.lumen.mapper.QuestionMapper;
import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.Comment;
import com.lean.lumen.model.CommentExample;
import com.lean.lumen.model.User;
import com.lean.lumen.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    QuestionMapper questionMapper;

    @Resource
    UserMapper userMapper;

    @Transactional
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

    public List<CommentDTO> listByQuestionId(Integer id, Integer type) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(Long.valueOf(id))
                .andTypeEqualTo(Long.valueOf(type)); // questionType = 1
        List<Comment> comments = commentMapper.selectByExample(example);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        List<Integer> commentators = comments.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(commentators);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setParentId(comment.getParentId().intValue());
            commentDTO.setType(comment.getType().intValue());
            commentDTO.setLikeCount(comment.getLikeCount().intValue());
            commentDTO.setUser(userMap.get(comment.getCommentator()));

            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
