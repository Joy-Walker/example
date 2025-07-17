package com.example.helper;

public interface IdempotencyService {
    /**
     * 校验幂等性
     * @param key 唯一消息Key
     * @return true-未处理过，可以继续处理；false-已处理过
     */
    boolean checkAndSet(String key);
}