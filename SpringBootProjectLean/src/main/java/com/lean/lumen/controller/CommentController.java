package com.lean.lumen.controller;

import com.lean.lumen.dto.CommentCreateDTO;
import com.lean.lumen.dto.ResultDTO;
import com.lean.lumen.model.Comment;
import com.lean.lumen.model.User;
import com.lean.lumen.service.CommentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
//        if (user == null){
//            return ResultDTO.errorOf(2002, "未登录");
//        }

        Comment comment = new Comment();
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setCommentator(commentCreateDTO.getCommentator());
        comment.setContent(commentCreateDTO.getContent());
        comment.setLikeCount(0L);

        commentService.createComment(comment);

        return ResultDTO.okOf();
    }
}
