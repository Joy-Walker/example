package com.test.jd.lifecycle;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-01 17:49:00
 */
public interface LifeCycle {

    void init() throws LifeCycleException;

    void start() throws LifeCycleException;

    void stop() throws LifeCycleException;

    void destroyed() throws LifeCycleException;
}
