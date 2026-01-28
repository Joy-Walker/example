package com.example.proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:27:00
 */
public class InterceptorB implements Interceptor{
    @Override
    public Object intercept(Object target) {
        System.out.println("InterceptorB execute");
        return target;
    }
}
