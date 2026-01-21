package com.java.dserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanWithCreator {
    public int id;
    public String name;


    /**
     * 告诉 Jackson 放弃默认的“无参构造函数 + Setter”流程，改用你指定的构造函数或静态工厂方法来创建对象。
     * 在构造参数上时必须和 @JsonProperty搭配使用，否则 Jackson 在编译后的字节码里找不到参数名，不知道把 JSON 的哪个 Key 传给哪个参数。
     *   @JsonCreator 必须标注在构造方法或者静态工厂方法上，因为对象还没有构造出来，普通方法没法调用
     * @param id
     * @param name
     */
    @JsonCreator
    public BeanWithCreator(
      @JsonProperty("id") int id,
      @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }

    @JsonCreator
    public static BeanWithCreator create( @JsonProperty("id") int id,@JsonProperty("theName") String name) {
        System.out.println(111111);
        return new BeanWithCreator(id, name);
    }


    public String toString() {
        return "BeanWithCreator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}