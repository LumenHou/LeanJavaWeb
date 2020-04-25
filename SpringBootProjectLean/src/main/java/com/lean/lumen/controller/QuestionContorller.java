package com.lean.lumen.controller;


import com.lean.lumen.dto.CommentDTO;
import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.service.CommentService;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionContorller {

    @Resource
    QuestionService questionService;

    @Resource
    CommentService commentService;

    @GetMapping("/questionDetails/{id}")
    public String question(@PathVariable(value = "id") Integer id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getQuestionById(id);

        List<CommentDTO> comments = commentService.listByQuestionId(id);

        questionService.incView(questionDTO.getId());
        model.addAttribute("questionDetails", questionDTO);
        model.addAttribute("comments", comments);

        return "questionDetails";
    }
}
