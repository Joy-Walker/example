package com.test.jd.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author :panligang
 * @description :
 * @create :2022-10-19 12:02:00
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new Task());

        Thread thread = new Thread(futureTask);

        futureTask.cancel(true);

        thread.start();

        System.out.println(futureTask.get());

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("123");
            return 1;
        });
    }


    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(100000);
            return "hello";
        }
    }
}
