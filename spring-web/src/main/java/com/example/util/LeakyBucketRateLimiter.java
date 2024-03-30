package com.example.util;

import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter {
    private final int capacity; // 漏桶容量
    private final int rate; // 漏水速率 1s流出多少个
    private int water; // 当前水量
    private long lastLeakTime; // 上次漏水时间

    public LeakyBucketRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        // 计算经过的时间
        long elapsedTime = currentTime - lastLeakTime;
        // 计算漏水量
        int leakedWater = (int) (elapsedTime * rate / 1000);
        // 更新上次漏水时间
        lastLeakTime = currentTime;
        // 漏桶中的水量减少
        water = Math.max(0, water - leakedWater);
        // 检查漏桶是否还有容量
        if (water < capacity) {
            // 漏桶中的水量增加
            water++;
            return true; // 请求通过限流
        }
        return false; // 请求被限流
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(50, 50);

        for (int i = 0; i < 100; i++) {
            if (rateLimiter.tryAcquire()) {
                System.out.println("Request " + (i + 1) + " allowed.");
            } else {
                System.out.println("Request " + (i + 1) + " limited.");
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}