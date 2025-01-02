package com.test.jd.design.template;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * @author :panligang
 * @description :
 * @create :2023-04-26 10:46:00
 */
public class LockTemplate<T,R> {

    public  R  execute(Function<T,R> function,T t) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            return function.apply(t);
        }finally {
            lock.unlock();
        }
    }

}
