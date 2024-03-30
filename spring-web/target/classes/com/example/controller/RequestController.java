package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :panligang
 * @description :
 * @create :2024-01-18 10:29:00
 */
@RestController
public class RequestController {

    Logger logger = LoggerFactory.getLogger(RequestController.class);

    @RequestMapping("/test1")
    public void test1(HttpServletRequest request) {

        logger.info("thread:{},request1:{}",Thread.currentThread().getName(),request.hashCode(),request);
    }

    @RequestMapping("/test2")
    public void test2(HttpServletRequest request) {
        logger.info("thread:{},request2:{}",Thread.currentThread().getName(),request.hashCode(),request);
    }

    @GetMapping("/getTest")
    public String getTest(HttpServletRequest request) {
        String age = request.getParameter("age");
        System.out.println("age=" + age);
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String age1 = request.getParameter("age");
            System.out.println("age1=" + age1);
        }).start();
        return "success";
    }
}
