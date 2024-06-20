package com.example.service.impl;

import com.example.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-20 19:34:00
 */
@Service
public class RedisSequenceServiceImpl implements SequenceService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public long getSequence(String namespace) {
        return Optional.ofNullable(stringRedisTemplate.opsForValue().increment(namespace)).orElse(0L);
    }
}
