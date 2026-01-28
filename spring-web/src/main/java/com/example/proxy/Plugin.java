package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:19:00
 */
public class Plugin implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {

        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object wrap(Object target, Interceptor interceptor){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Plugin(target, interceptor));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         interceptor.intercept(target);
         return method.invoke(target, args);
    }
}
