package com.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-04 19:06:00
 */
public class Main {

    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            countDownLatch.countDown();
            System.out.println(111);
        }).start();
        countDownLatch.await();


    }
}
