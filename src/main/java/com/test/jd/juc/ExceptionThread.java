package com.test.jd.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-01 16:41:00
 */
public class ExceptionThread {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println("default" + e.getCause());
        });

        Thread thread = new Thread(() -> {
            System.out.println(123);
            int i = 1 / 0;
            System.out.println(234);
        });
        thread.setUncaughtExceptionHandler((t,ex)->{
            System.out.println(ex.getMessage());
        });
        thread.start();



        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.println("current thread name" + Thread.currentThread().getName());
                Object object = null;
                System.out.print("result## "+object.toString());
            });
        }
        System.out.println("执行结束");
    }
}
