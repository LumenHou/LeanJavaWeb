package com.lean.lumen.controller;

import com.lean.lumen.dto.PaginationDTO;
import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class indexContorller {
    @Resource
    QuestionService questionService;


    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        // 打开首页时去查询cookie, 如果查询到cookies的话, 就存入session并自动登录

        PaginationDTO<QuestionDTO> paginationQuestion = questionService.question_list(page, size);
        model.addAttribute("questions", paginationQuestion);
        return "index";
    }
}
