package com.java.serialization;

import com.fasterxml.jackson.annotation.JsonRootName;


/**
 *
 *  mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
 *  默认情况下，只会把属性值序列化到中 如：{"name":"xxx","1d":123}
 *  mapper.enable(SerializationFeature.WRAP_ROOT_VALUE); 开启这个属性后，会把加一个根结点
 *  {"user":{"id":123,"name":"xxx"}}
 *  并且这个根结点的值可以通过@JsonRootName自定义
 */
@JsonRootName(value = "user")
public class UserWithRoot {
    public int id;
    public String name;

    public UserWithRoot(int id, String name) {
        this.id = id;
        this.name = name;
    }
}