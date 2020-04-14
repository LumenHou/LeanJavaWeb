package com.lean.lumen.controller;

import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.model.Question;
import com.lean.lumen.model.User;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishContorller {

    @Resource
    QuestionService questionService;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(Question question,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        if(question.getDescription() == null || question.getDescription().equals("")){
            model.addAttribute("error", "内容为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error", "未登录");
            return "index";
        }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionService.createOrUpdate(question);
        return "redirect:/";
    }


    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable("id") Integer id,
                               Model model) {
        QuestionDTO question = questionService.getQuestionById(id);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        return "publish";
    }
}
