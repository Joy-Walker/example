package com.test.jd.chain;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-17 17:19:00
 */
public interface Filter {

    public void init(FilterConfig config);

    public void doFilter(Object msg,FilterChain chain);

    public void destory();
}
