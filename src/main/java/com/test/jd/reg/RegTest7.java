package com.test.jd.reg;

import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description : 分组 捕获
 * @create :2023-06-01 21:45:00
 */
public class RegTest7 {

    public static void main(String[] args) {
        String content = "a ...a bvd..c...e";
        System.out.println(content.replaceAll("\\.",""));

        content = "aabbccccdddeee";

        // 去掉重复的字符
        String s = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");
        System.out.println(s);
    }
}
