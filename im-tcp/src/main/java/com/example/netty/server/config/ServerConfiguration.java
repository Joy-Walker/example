package com.example.netty.server.config;

import com.example.netty.server.IMServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 20:28:00
 */
@Configuration
public class ServerConfiguration {


    @ConfigurationProperties(prefix = "im.server")
    @Bean
    public IMServer imServer() {
        return new IMServer();
    }
}
