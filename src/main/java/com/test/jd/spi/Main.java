package com.test.jd.spi;

import java.util.ServiceLoader;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-06 22:09:00
 */
public class Main {

    public static void main(String[] args) {

        ServiceLoader<SPIService> serviceLoader = ServiceLoader.load(SPIService.class);

        for(SPIService service : serviceLoader) {
            System.out.println(service.getClass().getSimpleName());
            System.out.println(service.echo("hello"));
        }
    }
}
