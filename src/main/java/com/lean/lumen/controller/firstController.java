package com.lean.lumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class firstController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "lumen first boot";
    }
}
