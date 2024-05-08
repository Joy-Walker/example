package com.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-19 23:24:00
 */
public class SpringContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
