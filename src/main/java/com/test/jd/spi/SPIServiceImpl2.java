package com.test.jd.spi;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-06 22:07:00
 */
public class SPIServiceImpl2 implements SPIService {
    @Override
    public String echo(String s) {
        return s + "SPIServiceImpl2";
    }
}
