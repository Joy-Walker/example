package com.test.jd.design.strategy;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-19 16:33:00
 */
public interface ProcessInterface<T> {

    boolean support(T t);

    Object process(T t);



    public static void main(String[] args) {


    }
}

class A implements ProcessInterface{




    @Override
    public boolean support(Object o) {
        return "A".equals(o);
    }

    @Override
    public Object process(Object o) {
        return "A";
    }
}

class B implements ProcessInterface{

    @Override
    public boolean support(Object o) {
        return false;
    }

    @Override
    public Object process(Object o) {
        return "A";
    }
}
