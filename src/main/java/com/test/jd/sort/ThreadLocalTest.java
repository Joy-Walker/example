package com.test.jd.sort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 线程在第一次创建线程的时候，会将inheritableThreadLocal值 从父线程copy到子线程，
 * 仅在第一次创建的时候
 */
public class ThreadLocalTest {

    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        inheritableThreadLocal.set("i am a inherit parent");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                inheritableThreadLocal.remove();
                inheritableThreadLocal.set("111");
                System.out.println(inheritableThreadLocal.get());
            }
        });


        TimeUnit.SECONDS.sleep(1);
        inheritableThreadLocal.set("i am a new inherit parent");// 设置新的值


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                inheritableThreadLocal.remove();
                inheritableThreadLocal.set("222");
                System.out.println(inheritableThreadLocal.get());
            }
        });
    }
}
