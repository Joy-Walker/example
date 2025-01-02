package com.test.jd.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :panligang
 * @description :
 * @create :2023-06-01 17:46:00
 */
public class RegTest5 {

    public static void main(String[] args) {
        // 判断是否为汉字

        String content = "你好";

        // 匹配中文汉字
        // String regStr = "^[\u0391-\uffe5]+$";
        String regStr = "^[1-9](\\d{5})*$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        // 整天匹配
        System.out.println(Pattern.matches(regStr,"12222233333"));

//       if(matcher.find()) {
//           System.out.println("满足格式");
//       }else{
//           System.out.println("不满足格式");
//       }
//       while (matcher.find()) {
//           System.out.println(matcher.group(0 ));
//       }

    }
}
