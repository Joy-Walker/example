package com.example.proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:27:00
 */
public class InterceptorA implements Interceptor{
    @Override
    public Object intercept(Object target) {
        System.out.println("InterceptorA execute");
        return target;
    }
}
