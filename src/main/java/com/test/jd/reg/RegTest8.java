package com.test.jd.reg;

import java.util.Arrays;

/**
 * @author :panligang
 * @description : 字符串分割，可以使用正则表达式
 * @create :2023-06-03 10:40:00
 */
public class RegTest8 {

    public static void main(String[] args) {
        String content = "a.bc,c#de.f";
        String[] array = content.split("[.,#]");
        System.out.println(Arrays.toString(array));
    }
}
