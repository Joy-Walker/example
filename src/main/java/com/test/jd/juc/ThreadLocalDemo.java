package com.test.jd.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

    private static InheritableThreadLocal<Integer> local = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        local.set(4567);


        ExecutorService executorService = Executors.newSingleThreadExecutor();//线程池

        executorService.execute(() -> {//1
//            local.set(123);
            System.out.println(Thread.currentThread().getName() + "==>" + local.get());
//            local.remove();
        });

        local.remove();

        executorService.execute(() ->//2
                System.out.println(Thread.currentThread().getName() + "==>" + local.get())
        );

        System.out.println(Thread.currentThread().getName() + "==>" + local.get());

        executorService.shutdown();
    }





}
