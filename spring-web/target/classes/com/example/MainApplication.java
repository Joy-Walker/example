package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.List;


/**
 * @author :panligang
 * @description :
 * @create :2023-12-28 14:51:00
 */
@SpringBootApplication
public class MainApplication {
    static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        logger.info("11111");
        SpringApplication.run(MainApplication.class);

    }


}
