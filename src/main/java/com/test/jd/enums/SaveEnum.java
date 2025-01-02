package com.test.jd.enums;

import java.util.Arrays;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-08 14:30:00
 * 基于枚举 实现策略模式
 */

public enum SaveEnum {

    DB("db") {
        @Override
        public void save(Object obj) {
            System.out.println("保存到db中");
        }
    },

    CACHE("cache") {
        @Override
        public void save(Object obj) {
            System.out.println("保存到cache中");
        }
    },
    ;
    private final String type;

    SaveEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SaveEnum typeOf(String type) {
        return Arrays.stream(SaveEnum.values())
                .filter(o -> o.getType().equals(type))
                .findAny().orElse(null);
    }

    public abstract void save(Object obj);

    public static void main(String[] args) {
        String type = "db";
        SaveEnum.typeOf(type).save("aaaa");
    }
}
