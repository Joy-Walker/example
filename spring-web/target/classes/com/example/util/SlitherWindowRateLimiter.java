package com.example.util;

import java.util.LinkedList;
import java.util.Queue;

public class SlitherWindowRateLimiter {
    private final int window; // 窗口大小 比如 1000ms
    private final long rateLimit; // 频率限制
    private final Queue<Long> reqQueue; // 请求队列 维护每个请求到达的时间戳

    public SlitherWindowRateLimiter(int window, long rateLimit) {
        this.window = window;
        this.rateLimit = rateLimit;
        this.reqQueue = new LinkedList<>();
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - window;

        // 移除过期的请求
        while (!reqQueue.isEmpty() && reqQueue.peek() <= windowStart) {
            reqQueue.poll();
        }

        // 判断请求是否超过限制
        if (reqQueue.size() < rateLimit) {
            reqQueue.offer(currentTime);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}