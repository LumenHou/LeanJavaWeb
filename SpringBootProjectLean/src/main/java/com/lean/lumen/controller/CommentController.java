package com.lean.lumen.controller;

import com.lean.lumen.dto.CommentDTO;
import com.lean.lumen.mapper.CommentMapper;
import com.lean.lumen.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setParentId(commentDTO.getParentID());
        comment.setType(commentDTO.getType());
//        User user = (User)request.getSession().getAttribute("user");
        comment.setCommentator(1);
        comment.setContent(commentDTO.getContent());

        int i = commentMapper.insert(comment);
        if (i == 1) {

        } else {

        }

        return null;
    }
}
