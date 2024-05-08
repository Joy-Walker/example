package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @author :panligang
 * @description :
 * @create :2023-12-14 17:47:00
 */
@RestController
public class Test {


    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/test")
    public LocalDateTime test() {
        return LocalDateTime.now();
    }

    @RequestMapping("/testEnv")
    public String testEnv() {

        ResponseEntity<String> forEntity = restTemplate
                .getForEntity("http://www.baidu.com?key={}&value={}", String.class,"key","value" );
        return forEntity.getBody();
    }

}
