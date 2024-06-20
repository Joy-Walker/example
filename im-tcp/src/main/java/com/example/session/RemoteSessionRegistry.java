package com.example.session;

import com.example.pack.LoginPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-19 23:08:00
 */
@Component
public class RemoteSessionRegistry implements SessionRegistry {

    @Autowired
    StringRedisTemplate redisTemplate ;

    @Override
    public void register(String sessionId, String topic) {
        redisTemplate.opsForValue().set(sessionId,topic);
    }

    @Override
    public String getTopic(String sessionId) {
        return  redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void remove(String sessionId) {
        redisTemplate.delete(sessionId);
    }
}
