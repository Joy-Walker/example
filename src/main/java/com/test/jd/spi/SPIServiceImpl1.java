package com.test.jd.spi;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-06 22:06:00
 */
public class SPIServiceImpl1 implements SPIService {
    @Override
    public String echo(String s) {
        return s + "SPIServiceImpl1";
    }
}
