package com.example.util;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterSimpleWindow {
    // 阈值
    private static Integer QPS = 20;
    // 时间窗口（毫秒）
    private static long TIME_WINDOWS = 1000;
    // 计数器
    private static AtomicInteger REQ_COUNT = new AtomicInteger();
    
    private static long START_TIME = System.currentTimeMillis();

    public synchronized static boolean tryAcquire() {
        if ((System.currentTimeMillis() - START_TIME) > TIME_WINDOWS) {
            REQ_COUNT.set(0);
            START_TIME = System.currentTimeMillis();
        }
        return REQ_COUNT.incrementAndGet() <= QPS;
    }

    public static void main(String[] args) throws InterruptedException {
        //每秒20个请求
        int count = 0;
        //模拟50次请求，看多少能通过
        for (int i = 0; i < 50; i++) {
            if (tryAcquire()) {
                count++;
            }
        }
        System.out.println("第一拨50次请求中通过：" + count + ",限流：" + (50 - count));
        //过一秒再请求
        Thread.sleep(2000);
        //模拟50次请求，看多少能通过
        count = 0;
        for (int i = 0; i < 50; i++) {
            if (tryAcquire()) {
                count++;
            }
        }
        System.out.println("第二拨50次请求中通过：" + count + ",限流：" + (50 - count));
    }
}