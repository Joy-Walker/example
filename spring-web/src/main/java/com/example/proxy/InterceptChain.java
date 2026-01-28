package com.example.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:25:00
 */
public class InterceptChain {

    private final List<Interceptor> interceptors = new ArrayList<>();


    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
}
