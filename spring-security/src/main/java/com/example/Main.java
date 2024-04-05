package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-02 16:17:00
 */

@SpringBootApplication
@EnableAuthorizationServer
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

}
