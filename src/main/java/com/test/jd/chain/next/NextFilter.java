package com.test.jd.chain.next;


import com.test.jd.chain.Filter;
import com.test.jd.chain.FilterChain;
import com.test.jd.chain.FilterConfig;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-17 17:41:00
 */
public class NextFilter implements Filter {

    private Filter nextFilter;

    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(Object msg, FilterChain chain) {
        System.out.println(123);
        nextFilter.doFilter(msg,chain);
    }

    @Override
    public void destory() {

    }


}
