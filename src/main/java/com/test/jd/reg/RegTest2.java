package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-31 19:16:00
 */
public class RegTest2 {

    public static void main(String[] args) {
        String content = "abc1\" ($ abcd(aBCCA_@"     ;

        // String regStr = "[a-z]";  // 匹配a-z中的任意一个字符
        // String regStr = "[A-Z]";     // 匹配A-Z中的任意一个字符
        // String regStr = "[^A-Z]";     // 匹配不在A-Z中的任意一个字符
        // String regStr = "a(?i)(b)c";     // 匹配abc 或者 aBc (?i)表示当前字符不区分大小写
        // String regStr = "a(?i)bc"; // 表示bc都不区分大小写 匹配aBC abc
        // String regStr = "[0-9]"; // 匹配0-9中的任意一个字符
        // String regStr = "\\d"; // 匹配一个数字
        // String regStr = "\\D"; // 匹配一个非数字

        // String regStr = "\\w"; // 匹配数字（大小写字母）下划线

        String regStr = "\\s"; // 匹配任意空白字符

        // 匹配时 不区分字母的大小写
        Pattern pattern = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到" + matcher.group(0));
        }
    }
}
