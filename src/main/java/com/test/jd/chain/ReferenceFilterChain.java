package com.test.jd.chain;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-17 17:33:00
 */
public class ReferenceFilterChain implements FilterChain{

    /**
     * 下一个过滤器链
     */
    private FilterChain nextChain;

    private Filter filter;

    @Override
    public void doFilter(Object msg) {
        if(filter != null) {
            filter.doFilter(msg,nextChain);
        }
    }
}
