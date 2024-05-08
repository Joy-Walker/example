package com.example.init;

import com.example.pack.LoginPack;
import com.example.server.IMServer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
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
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {

        redisTemplate.opsForValue().set("key",new LoginPack());
        LoginPack loginPack = (LoginPack) redisTemplate.opsForValue().get("key");

        logger.info("redis test:{}",redisTemplate.opsForValue().get("key"));
        logger.info("imServer starting port:{}",imServer.getPort());
        rocketMQTemplate.sendOneWay("testTpic", MessageBuilder.withPayload("hello world"));

        imServer.run();
    }
}
