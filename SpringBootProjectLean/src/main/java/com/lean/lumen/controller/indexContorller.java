package com.lean.lumen.controller;

import com.lean.lumen.dto.PaginationDTO;
import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.mapper.QuestionMapper;
import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.User;
import com.lean.lumen.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexContorller {

    @Resource
    UserMapper userMapper;

    @Resource
    QuestionService questionService;


    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        // 打开首页时去查询cookie, 如果查询到cookies的话, 就存入session并自动登录
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        PaginationDTO<QuestionDTO> paginationQuestion = questionService.question_list(page, size);
        model.addAttribute("questions", paginationQuestion);
        return "index";
    }
}
