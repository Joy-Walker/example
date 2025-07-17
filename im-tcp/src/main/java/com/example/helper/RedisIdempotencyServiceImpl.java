package com.example.helper;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisIdempotencyServiceImpl implements IdempotencyService {

    private final StringRedisTemplate redisTemplate;

    public RedisIdempotencyServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean checkAndSet(String key) {
        // 原子操作，setnx
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, "1", 24, TimeUnit.HOURS);
        return Boolean.TRUE.equals(success);
    }
}