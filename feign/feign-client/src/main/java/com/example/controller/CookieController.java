package com.example.controller;

import com.example.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

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
        cookie.setDomain("jd.com");
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

        request.getSession().getMaxInactiveInterval();

        response.sendRedirect("http://www.baidu.com");
        return "111";
    }








    @GetMapping("/test3")
    public String test3(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域名访问，生产环境建议指定具体域名
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Expose-Headers", "*");
        String queryString = request.getQueryString();
        return "111";
    }

    @GetMapping("/test4")
    public String test4(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user",new User("panligang"));
        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        return "ok";
    }

    @GetMapping("/test5")
    public String test5(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Object user = session.getAttribute("user");
        return "ok";
    }

    @GetMapping("/{version}/test6/")
    public String test6(@PathVariable String version) {




        return version;
    }


    @GetMapping("/test8")
    public String test8(HttpServletRequest request) throws Exception {
       String s = request.getReader().lines().collect(Collectors.joining());
        s = request.getReader().lines().collect(Collectors.joining());
        request.getInputStream().read();
        return "ok";
    }
    @GetMapping("/test-stream")
    public void testStream(HttpServletResponse response) throws IOException {
        // 设置返回类型
        response.setContentType("text/plain;charset=UTF-8");

        // 1. 拿到输出流
        ServletOutputStream out = response.getOutputStream();

        // 2. 写出一段数据
        out.print("Hello Client!\n");

        // 3. flush（立即把内容写给客户端，但不会结束方法执行）
        out.flush();

        System.out.println("=== 已经写出数据，但方法还在继续执行 ===");

        // 4. 再写一段数据
        out.print("More data after flush\n");
        out.flush();

        // 5. 这里即使关闭流，后面的代码也还会执行
        out.close();

        System.out.println("=== 即使关闭流，后续代码也执行 ===");

        // 6. 如果这里再写，就会抛异常（Stream closed）
         out.print("This will fail\n");
         out.flush();

        System.out.println("=== 2即使关闭流，后续代码也执行 ===");
    }










}
