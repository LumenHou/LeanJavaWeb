package com.lean.lumen.controller;

import com.lean.lumen.bean.User;
import com.lean.lumen.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class indexController {

    @Autowired
    UserService userService;


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String go() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "register";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        // 打开首页时去查询cookie, 如果查询到cookies的话, 就存入session并自动登录
        System.out.println("userName = " + username + ", password = " + password);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }

    @GetMapping("/login")
    public String test() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "index";
    }
}
