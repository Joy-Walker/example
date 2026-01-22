package com.example.sensitive.aop;

import com.example.sensitive.Sensitive;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

//@Aspect
//@Component
public class SensitiveAspect {

    @AfterReturning(
            pointcut = "execution(* com.example.controller..*(..)) || " +
                    "@within(org.springframework.web.bind.annotation.RestController)",
            returning = "result"
    )
    public void afterReturning(Object result) {
        handleObject(result);
    }

    private void handleObject(Object result) {
        // Iterable 涵盖了所有集合 处理集合
        if (result instanceof Iterable) {
            for (Object o : (Iterable) result) {
                handleObject(o);
            }
            return;
        }
        // 处理数组
        if (result.getClass().isArray()) {
            int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                handleObject(Array.get(result, i));
            }
            return;
        }
        handle(result);
    }

    private void handle(Object o) {

    }
}