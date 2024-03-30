package com.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2023-12-27 11:47:00
 */
@ConfigurationProperties(prefix = "test")
@Component
public class TestConfig {
    private String name;
    /**
     * test:
     *   test:
     *     name: 111
     *  test:
     *    a:
     *      name: 111
     *
     */
    private A test =  new A();

    public A getA() {
        return test;
    }

    public static class A {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
