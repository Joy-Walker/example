package com.test.jd.chain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-17 17:25:00
 */
public class IteratorFilterChain implements FilterChain{

    private List<Filter> filters;

    private Iterator<Filter> iterator;

    public  IteratorFilterChain () {
        initFilterChain();
    }

    private void initFilterChain() {
        filters = new ArrayList<>();
    }

    @Override
    public void doFilter(Object msg) {
        if(iterator == null) {
            iterator = filters.iterator();
        }

        if(iterator.hasNext()) {
            Filter next = iterator.next();
            next.doFilter(msg,this);
        }
    }
}
