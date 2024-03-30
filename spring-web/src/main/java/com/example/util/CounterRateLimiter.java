package com.example.util;

import java.util.concurrent.atomic.AtomicLong;

public class CounterRateLimiter {
    /**
     * 单位时间窗口内的最大请求数
     */
    private final long limit;

    /**
     * 上一个窗口的开始时间
     */
    private long timestamp;
    /**
     * 计数器
     */
    private final AtomicLong counter;

    /**
     * 时间窗口 单位 s
     */
    private final int window;


    public CounterRateLimiter(long limit, int window) {
        this.limit = limit;
        this.window = window;
        this.timestamp = System.currentTimeMillis();
        this.counter = new AtomicLong(0);
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 时间窗口过期，重置计数器和时间戳
        if (now - timestamp > window) {
            counter.set(0L);
            timestamp = now;
        }
        long currentCount = counter.incrementAndGet();
        return currentCount <= limit;
    }

    public static void main(String[] args) throws InterruptedException {
        //每秒20个请求
        CounterRateLimiter counterLimiter = new CounterRateLimiter(10, 1000);
        int count = 0;
        //模拟50次请求，看多少能通过
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第一拨50次请求中通过：" + count + ",限流：" + (50 - count));
        //过一秒再请求
        Thread.sleep(2000);
        //模拟50次请求，看多少能通过
        count = 0;
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第二拨50次请求中通过：" + count + ",限流：" + (50 - count));
    }

}