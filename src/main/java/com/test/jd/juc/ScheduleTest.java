package com.test.jd.juc;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :panligang
 * @description :
 * @create :2022-11-17 09:35:00
 */
public class ScheduleTest {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);



        Task task = new Task(executorService);
//
        executorService.schedule(task,3,TimeUnit.SECONDS);

    }

    static class Task implements Runnable {

        ScheduledExecutorService scheduledExecutorService;

        public Task(ScheduledExecutorService executorService) {
            this.scheduledExecutorService = executorService;
        }

        @Override
        public void run() {
            System.out.println(123);
            scheduledExecutorService.schedule(this,4,TimeUnit.SECONDS);
        }
    }
}
