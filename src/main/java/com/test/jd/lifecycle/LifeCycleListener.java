package com.test.jd.lifecycle;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-05 14:16:00
 */
@FunctionalInterface
public interface LifeCycleListener {

    void onEvent(LifeCycleEvent lifeCycleEvent);
}
