package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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



    public static String createQueryParameters(String api, Map<String, String> conditions) {
        String params = conditions.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining("&"));
        return api + "?" + params;
    }

}
