package com.java.serialization;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    TypeEnumWithValue(int  id, String name) {
        this.id = id;
        this.name = name;
    }


    // standard constructors

    /**
     * 对象转json时会调用@JsonValue标注的方法，当有多个时会报错
     * @return
     */
    @JsonValue
    public String getName() {
        return name;
    }

    @JsonValue
    public String toString() {
        return name;
    }
}