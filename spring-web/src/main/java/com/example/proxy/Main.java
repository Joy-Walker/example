package com.example.proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:24:00
 */
public class Main {

    public static void main(String[] args) {

        InterceptChain interceptChain = new InterceptChain();
        interceptChain.addInterceptor(new InterceptorA());
        interceptChain.addInterceptor(new InterceptorB());

        Executor executor = new DefaultExecutor();

        Executor o = (Executor) interceptChain.pluginAll(executor);
        o.execute("aaa",null);


    }
}
