package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :panligang
 * @description :
 * @create :2024-11-27 19:23:00
 */
@RestController
public class CookieController {

    @GetMapping("/test1")
    public String test1(HttpServletRequest request,HttpServletResponse response) {
        Cookie cookie = new Cookie("jd-cookie","123");
        cookie.setPath("/");
        cookie.setDomain("www.jd.com");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        return "111";
    }

    /**
     * 跨域检查：浏览器的跨域检查主要针对AJAX请求（如XMLHttpRequest或Fetch API）。
     * 对于普通的重定向请求，浏览器不会进行跨域检查，而是直接跳转到新的URL。
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/test12")
    public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域名访问，生产环境建议指定具体域名
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.sendRedirect("https://www.baidu.com");
        return "111";
    }

    @GetMapping("/test3")
    public String test3(HttpServletRequest request,HttpServletResponse response) {
        String queryString = request.getQueryString();
        return "111";
    }


}
