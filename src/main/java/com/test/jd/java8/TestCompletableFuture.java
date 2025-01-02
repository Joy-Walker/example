package com.test.jd.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-25 10:54:00
 */
public class TestCompletableFuture {

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> completableFuture = new CompletableFuture<>();
//
//        CompletableFuture.supplyAsync(() -> 1 / 2)
//                .whenCompleteAsync((ret, ex) -> {
//                    if (ex == null) {
//                        completableFuture.complete(ret.toString());
//                    } else {
//                        completableFuture.completeExceptionally(ex);
//                    }
//                });

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1 / 2).whenComplete((ret, ex) -> {
//            if(ex == null) {
//                System.out.println(ret);
//            }else {
//                System.out.println(ex.getMessage());
//            }
//        });
//        System.out.println(future.get());

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> 1 / 0).thenApply(ret -> ret.toString());
        System.out.println(completableFuture.get());

//        CompletableFuture.supplyAsync(()-> 1 / 0).exceptionally()


//        System.out.println(test().get());
//        test().thenAccept(s-> System.out.println(s));
//
//        while (true) {
//            System.out.println(11111);
//            sleep(1000);
//            if(false) {
//                break;
//            }
//        }
    }


    public static CompletableFuture<String> test() {
        sleep(3000);
        CompletableFuture.completedFuture("123");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("123");

        return CompletableFuture.completedFuture("123");
    }
}
