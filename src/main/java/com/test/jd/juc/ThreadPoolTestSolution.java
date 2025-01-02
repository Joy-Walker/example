package com.test.jd.juc;

import org.springframework.core.task.TaskDecorator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTestSolution {

    public static void main(String[] args) {
        ThreadLocalHolder.userId.set("77");
        System.out.println(Thread.currentThread().getName() + " userId : " + ThreadLocalHolder.userId.get());

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ThreadLocalHolder.userId.set("8888");
        for(int i = 0; i < 10 ; i++) {
            Runnable decorate = new ContextCopyingDecorator().decorate(new RunnableTest());
            executorService.execute(decorate);
        }

        executorService.shutdown();

    }
}

class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " userId : " + ThreadLocalHolder.userId.get());
    }
}

class ThreadLocalHolder {
    static ThreadLocal<String> userId = new ThreadLocal<>();
}

class WrapRunnable {

    //把runnable包装一下
    public static Runnable wrap(Runnable runnable) {
        //获取当前线程的ThreadLocalHolder
        String currentThreadLocal = ThreadLocalHolder.userId.get();
        return new Runnable() {
            @Override
            public void run() {
                try {
                    //塞到子线程的ThreadLocalHolder
                    ThreadLocalHolder.userId.set(currentThreadLocal);
                    //继续执行原来Runnable的run方法
                    runnable.run();
                } finally {
                    //最终清空下ThreadLocalHolder，防止线程池重复使用线程带来的ThreadLocal数据遗留问题
                    ThreadLocalHolder.userId.remove();
                }
            }
        };
    }
}

class ContextCopyingDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        String currentThreadLocal = ThreadLocalHolder.userId.get();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                ThreadLocalHolder.userId.set(currentThreadLocal);
                try {
                    runnable.run();
                } finally {
                    ThreadLocalHolder.userId.remove();
                }
            }
        };
        return runnable1;
    }
}

