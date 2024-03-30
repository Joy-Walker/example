package com.test.controller;

import com.test.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author :panligang
 * @description :
 * @create :2023-12-14 17:47:00
 */
@RestController
public class Test {

    @Autowired
    TestConfig testConfig;

    @RequestMapping("/test")
    @ResponseBody
    public LocalDateTime test(HttpServletResponse response) {
        return LocalDateTime.now();
    }

    @RequestMapping("/testEnv")
    public String testEnv(HttpServletRequest request) {
        return System.getProperty("name");
    }


}
