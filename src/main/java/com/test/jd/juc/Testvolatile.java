package com.test.jd.juc;


public class Testvolatile {
    public static  boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for(;;) {
                System.out.println(flag);
            }
        });

        Thread thread2 = new Thread(()->{
            for(;;){
                flag = true;
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
