package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 19:54:00
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.SERVLET)
                .main(Application.class)
                .build(args);
        application.run(args);
    }
}
