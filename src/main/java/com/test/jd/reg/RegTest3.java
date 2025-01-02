package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description :演示限定符
 * @create :2023-05-31 20:39:00
 */
public class RegTest3 {

    public static void main(String[] args) {
        String content = "FTP://github.com/";

        // String regStr = "a{3}";    // 表示匹配aaa
        //  String regStr = "\\d{2}";    // 表示匹配任意两个数字
        //String regStr = "1{2,3}";    // 表示匹配11 或者 111 ，111更优先
        // String regStr = "1*";    // 表示匹配0个或者多个1
        String regStr = "(?i)(https?|ftp)://";    // 表示匹配a或者a1
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("找到" + matcher.group(0));
        }
    }
}
