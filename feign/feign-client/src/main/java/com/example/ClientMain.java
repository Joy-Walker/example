package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-22 22:20:00
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.client")
public class ClientMain {
    public static void main(String[] args) {
        SpringApplication.run(ClientMain.class,args);
    }
}
