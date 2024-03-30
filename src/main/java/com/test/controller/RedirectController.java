package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
 
    @GetMapping("/test")
    public String redirectToBaidu() {
        return "redirect:https://www.baidu.com";
    }
}