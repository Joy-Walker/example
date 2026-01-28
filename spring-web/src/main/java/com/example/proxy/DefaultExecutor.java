package com.example.proxy;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-28 14:29:00
 */
public class DefaultExecutor implements Executor{
    @Override
    public void execute(String statement, Object[] args) {
        System.out.println("DefaultExecutor execute");
    }
}
