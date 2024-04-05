package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-02 16:17:00
 */


@RestController
public class DemoController {

    @RequestMapping("/demo")
    public String demo() {
        return "hello";
    }

}
