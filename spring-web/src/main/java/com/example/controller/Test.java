package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author :panligang
 * @description :
 * @create :2023-12-14 17:47:00
 */
@RestController
public class Test {


    @RequestMapping("/test")
    public LocalDateTime test() {
        return LocalDateTime.now();
    }

    @RequestMapping("/testEnv")
    public String testEnv() {
        return System.getProperty("name");
    }

}
