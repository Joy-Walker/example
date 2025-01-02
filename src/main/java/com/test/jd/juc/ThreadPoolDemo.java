package com.test.jd.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :panligang
 * @description :
 * @create :2022-10-13 09:36:00
 */
public class ThreadPoolDemo {


    static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        threadLocal.set("1234");

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(10),
                r->{
                    Thread t = new Thread(r);
                    t.setName("aaaa" + count.incrementAndGet());
                    t.setUncaughtExceptionHandler((thread,exception)->{
                        System.out.println(exception);
                    });
                    return t;
                }){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(threadLocal.get());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
               threadLocal.remove();
            }
        };


        executorService.execute(()->{
            System.out.println(threadLocal.get());
        });

        executorService.execute(()->{
            System.out.println(threadLocal.get());
        });

        executorService.execute(()->{
            System.out.println(threadLocal.get());
        });


    }
}
