package com.test.jd.lifecycle;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-01 17:51:00
 */
public class LifeCycleException extends Exception {


    public LifeCycleException(String message) {
        super(message);
    }

    public LifeCycleException(String message,Throwable throwable) {
        super(message,throwable);
    }

    public LifeCycleException(Throwable throwable) {
        super(throwable);
    }

}
