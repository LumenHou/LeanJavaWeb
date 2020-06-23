package com.lean.lumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/index")
    public String index() {
        // 打开首页时去查询cookie, 如果查询到cookies的话, 就存入session并自动登录
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        // 打开首页时去查询cookie, 如果查询到cookies的话, 就存入session并自动登录
        return "login";
    }
}
