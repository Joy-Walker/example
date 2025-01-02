package com.test.jd.design.template;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-25 17:31:00
 */
public class Main {

    public static void main(String[] args) {

        LockTemplate<String,String> lockTemplate = new LockTemplate<>();
        lockTemplate.execute(String::toLowerCase,"aaa" );
    }
}
