package com.example.session;

import com.example.pack.LoginPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-19 23:08:00
 */
@Component
public class RemoteSessionManager extends AbstractSessionManager {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void doAddSession(String sessionId, LoginPack loginPack) {
        redisTemplate.opsForValue().set(sessionId,loginPack);
    }

    @Override
    public LoginPack getSession(String sessionId) {
        return (LoginPack) redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void doRemoveSession(String sessionId) {
        redisTemplate.delete(sessionId);
    }
}