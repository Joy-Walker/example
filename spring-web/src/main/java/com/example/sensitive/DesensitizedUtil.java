package com.example.sensitive;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 10:04:00
 */
public class DesensitizedUtil {

    /**
     * TODO 真实的脱敏操作
     * @param value
     * @param type
     * @return
     */
    public static String desensitize(String value, Sensitive.SensitiveType type) {
       return "desensitize_" + value;
    }
}
