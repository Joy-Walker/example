package com.test.jd.chain;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-17 17:37:00
 */
public class Filter1 implements Filter{
    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(Object msg, FilterChain chain) {
        System.out.println("Filter1");
        chain.doFilter(msg);
    }

    @Override
    public void destory() {

    }
}
