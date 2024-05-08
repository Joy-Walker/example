package com.example.config;

import com.example.utils.SpringContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-19 23:35:00
 */
@Configuration
public class CommonConfig {

    @Bean
    public SpringContext springContext() {
        return new SpringContext();
    }
}
