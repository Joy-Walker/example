package com.example.config;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice   // 作用于所有 @RestController 或 @Controller
public class GlobalExceptionHandler {

    /**
     * 处理 Feign 调用异常
     */
    @ExceptionHandler(FeignException.class)
    public Map<String, Object> handleFeignException(FeignException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.SERVICE_UNAVAILABLE.value());
        result.put("message", "服务调用失败: " + ex.getMessage());
        result.put("type", "FeignException");
        return result;
    }

    /**
     * 处理 Feign 重试异常
     */
    @ExceptionHandler(RetryableException.class)
    public Map<String, Object> handleRetryableException(RetryableException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.REQUEST_TIMEOUT.value());
        result.put("message", "服务调用超时,已重试失败: " + ex.getMessage());
        result.put("type", "RetryableException");
        return result;
    }

    /**
     * 处理连接超时异常
     */
    @ExceptionHandler(SocketTimeoutException.class)
    public Map<String, Object> handleSocketTimeoutException(SocketTimeoutException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.REQUEST_TIMEOUT.value());
        result.put("message", "连接超时: " + ex.getMessage());
        result.put("type", "SocketTimeoutException");
        return result;
    }

    /**
     * 处理所有异常的通用方法
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.put("message", ex.getMessage());
        result.put("type", ex.getClass().getSimpleName());
        return result;
    }

    /**
     * 专门处理 ArithmeticException(比如 1/0)
     */
    @ExceptionHandler(ArithmeticException.class)
    public Map<String, Object> handleArithmeticException(ArithmeticException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 5001);
        result.put("message", "计算错误:" + ex.getMessage());
        result.put("type", "ArithmeticException");
        return result;
    }
}
