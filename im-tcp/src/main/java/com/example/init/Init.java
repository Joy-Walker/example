package com.example.init;

import com.example.server.IMServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 20:30:00
 */
@Component
public class Init implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Init.class);

    @Autowired
    IMServer imServer;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public void run(String... args) throws Exception {

        logger.info("redis test:{}",redisTemplate.opsForValue().get("key"));
        logger.info("imServer starting port:{}",imServer.getPort());
        imServer.run();
    }
}
