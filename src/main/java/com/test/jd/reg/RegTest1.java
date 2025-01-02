package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description : 演示转义字符
 * @create :2023-05-31 19:00:00
 */
public class RegTest1 {

    public static void main(String[] args) {
        String content = "abc123\"($abc(\n";
        // 在Java中 需要使用两个 \\ 对特殊字符进行转义
        String regStr = "\\d";

        Pattern pattern = Pattern.compile(regStr);

        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }


    }
}
