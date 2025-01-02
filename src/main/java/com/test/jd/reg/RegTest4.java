package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description : 定位符
 * @create :2023-05-31 21:37:00
 */
public class RegTest4 {

    public static void main(String[] args) {
        String content = "abc ab abcab";
        // 以至少一个数字开头，后跟任意个小写字母
        // String regStr = "^[0-9]+[a-z]*";    // ^ 表示以数字开头 + 表示1个或者多个，* 表示0个或者多个
        // 以至少一个数字开头，以至少一个字母结尾
        // String regStr = "^[0-9]+[a-z]+$";    // ^ 表示以数字开头 + 表示1个或者多个，* 表示0个或者多个
        // 以ab作为边界
        String regStr = "ab\\b";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("找到" + matcher.group(0));
        }
    }
}
