package com.example.controller;

import com.example.wrap.ByteResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author :panligang
 * @description :
 * @create :2025-10-27 17:12:00
 */
@RestController
@RequestMapping("/request")
public class RequestController {

    @RequestMapping("/test1")
    public String test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        name = request.getParameter("name");
        ServletInputStream inputStream = request.getInputStream();
        String body = readBody(inputStream);
        body = readBody(inputStream);
        return body;
    }

    private String readBody(ServletInputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
