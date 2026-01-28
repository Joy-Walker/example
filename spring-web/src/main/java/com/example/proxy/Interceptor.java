package com.example.proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:17:00
 */
public interface Interceptor {

     Object intercept(Object target) ;

     default Object plugin(Object target) {
         return Plugin.wrap(target,this);
     }
}
