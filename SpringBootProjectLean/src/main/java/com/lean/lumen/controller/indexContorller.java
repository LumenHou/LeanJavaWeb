package com.lean.lumen.controller;

import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class indexContorller {

    @Resource
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
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
        return "index";
    }

}
