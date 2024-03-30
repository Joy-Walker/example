package com.example.lock;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-28 20:26:00
 */
public interface DistributeLock {

    /**
     * 获取锁
     * @param key
     * @return
     */
    boolean lock(String key,String value);

    /**
     * 释放锁
     * @param key
     * @return
     */
    boolean unlock(String key);
}
