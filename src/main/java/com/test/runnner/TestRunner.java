package com.test.runnner;

import com.test.config.DemoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {

//    @Value("${demo.name}")
    String name;

//    @Value("${server.port}")
    String port;

    @Autowired
    DemoConfig demoConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(demoConfig);
        System.out.println(name);
        System.out.println(port);
    }
}