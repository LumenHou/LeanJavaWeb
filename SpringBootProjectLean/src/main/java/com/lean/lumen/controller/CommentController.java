package com.lean.lumen.controller;

import com.lean.lumen.dto.CommentCreateDTO;
import com.lean.lumen.dto.CommentDTO;
import com.lean.lumen.dto.ResultDTO;
import com.lean.lumen.model.Comment;
import com.lean.lumen.model.User;
import com.lean.lumen.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTO1s = commentService.listByQuestionId(id, 2);
        return ResultDTO.okOf(commentDTO1s);
    }
}
