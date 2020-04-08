package com.lean.lumen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restControllerTest {

    @RequestMapping("/rest")
    public String restString(){
        return "@restController 真好用";
    }
}
