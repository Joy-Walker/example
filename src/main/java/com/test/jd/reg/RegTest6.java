package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-01 21:29:00
 */
public class RegTest6 {

    public static void main(String[] args) {
        String content = "hello@hi.com";

        // 匹配两个连续相同的数字
        // String regStr = "(\\d)\\1";

        // 匹配五个连续相同的数字
        // String regStr = "(\\d)\\1{4}";

        // 匹配重复出现的单词
        // String regStr = "(\\w+) \\1";

        String regStr = "^[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$";

        if(content.matches(regStr)){
            System.out.println("匹配成功");
        }

        Pattern pattern = Pattern.compile(regStr);

        Matcher matcher = pattern.matcher(content);

        while(matcher.find()) {
            System.out.println("找到：" + matcher.group(0));
        }
    }
}
