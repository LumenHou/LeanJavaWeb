package com.lean.lumen.controller;


import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionContorller {

    @Resource
    QuestionService questionService;

    @GetMapping("/questionDetails/{id}")
    public String question(@PathVariable(value = "id") Integer id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getQuestionById(id);
        questionService.incView(questionDTO.getId());
        model.addAttribute("questionDetails", questionDTO);

        return "questionDetails";
    }
}
