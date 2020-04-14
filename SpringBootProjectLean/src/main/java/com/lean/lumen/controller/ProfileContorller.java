package com.lean.lumen.controller;

import com.lean.lumen.dto.PaginationDTO;
import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.model.User;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileContorller {

    @Resource
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          HttpServletRequest httpServletRequest,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if (action.equals("questions")){
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "我的问题");
        }
        if (action.equals("replies")){
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO<QuestionDTO> paginationQuestion = questionService.question_list(user.getId(), page, size);
        model.addAttribute("questions", paginationQuestion);

        return "profile";
    }
}
