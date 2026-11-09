package com.example.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static StringRedisTemplate redisTemplate;


    public static<T>  T getObject(String key,Class<T> cls) {
        return JsonUtils.fromJson(redisTemplate.opsForValue().get(key), cls);
    }


    public static void setObject(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, JsonUtils.toJson(value));
        redisTemplate.expire(key, timeout, timeUnit);
    }


    public static void main(String[] args) {
        String key = "aaa";
        setObject(key, "bbb", 1, TimeUnit.DAYS);
        System.out.println(getObject(key, String.class));
    }








}
