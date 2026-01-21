package com.example.controller;

import com.example.wrap.ByteResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author :panligang
 * @description :
 * @create :2025-10-27 17:12:00
 */
@RestController
@RequestMapping("/response")
public class ResponseController {

    @GetMapping("/test1")
    public String test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ByteResponseWrapper wrapper = new ByteResponseWrapper(response);
        writeJson(wrapper);
        String capturedString = wrapper.getCapturedString();
        return capturedString;
    }


    @GetMapping("/test2")
    public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeJson(response);
        response.resetBuffer();
        return "hello";
    }

    private void writeJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.write("{\"name\":\"张三\"}".getBytes(StandardCharsets.UTF_8));
//        out.flush();
    }
}
