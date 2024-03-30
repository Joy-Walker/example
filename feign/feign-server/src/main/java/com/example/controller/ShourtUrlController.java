package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-23 16:07:00
 */
@Controller
public class ShourtUrlController {


    Map<String,String> urlMap = new HashMap<>();

    Stack<String> stack = new Stack<>();

    String prefix = "http://127.0.0.1:10000/access?shortUrl=";

    @PostConstruct
    public void init() {
        stack.add("aaa");
        stack.add("bbb");
        stack.add("ccc");
        stack.add("ddd");
    }

    @RequestMapping("/url")
    @ResponseBody
    public String shourtUrl(@RequestParam("originUrl") String originUrl) {
        String shortUrl = stack.pop();
        urlMap.put(shortUrl,originUrl);
        return prefix + shortUrl;
    }

    @RequestMapping("/access")
    public void accessUrl(@RequestParam("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
       String originUrl = urlMap.get(shortUrl);
       response.sendRedirect(originUrl);
    }
}
