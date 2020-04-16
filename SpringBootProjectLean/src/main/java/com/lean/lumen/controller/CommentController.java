package com.lean.lumen.controller;

import com.lean.lumen.dto.CommentDTO;
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
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
//        if (user == null){
//            return ResultDTO.errorOf(2002, "未登录");
//        }

        Comment comment = new Comment();
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setParentId(commentDTO.getParentID());
        comment.setType(commentDTO.getType());
        comment.setCommentator(1);
        comment.setContent(commentDTO.getContent());
        comment.setLikeCount(0L);

        commentService.createComment(comment);

        return ResultDTO.okOf();
    }
}
