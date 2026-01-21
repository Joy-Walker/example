package com.java.dserialization;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.List;
import java.util.Map;

public class ExtendableBean {
    public String name;

    /**
     * 这玩意还可以标注在属性上，会直接生效
     */
//    @JsonAnySetter
    private Map<String, String> properties;
    private List<String> list;


    public ExtendableBean() {
        properties = new java.util.HashMap<>();
        list = new java.util.ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 添加属性 反转序列化时调用
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String toString() {
        return "ExtendableBean{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}