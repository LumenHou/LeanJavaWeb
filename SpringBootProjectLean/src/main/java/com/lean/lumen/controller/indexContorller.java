package com.lean.lumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexContorller {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
