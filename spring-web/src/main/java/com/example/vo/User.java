package com.example.vo;

import com.example.sensitive.Sensitive;
import com.example.sensitive.jackson.SensitiveByJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 10:19:00
 */
public class User {

    private String name;

    private int age;


    /**
     * @JsonSerialize(using = SensitiveByJsonSerializer.class)
     * 可以直接加在字段上，表明该字段需要做脱敏，使用SensitiveByJsonSerializer进行序列化
     * 但是呢不优雅，因为还需要标注Sensitive告诉具体的脱敏方式
     * <p>
     * 所以可以通过复合注解，把Sensitive和JsonSerialize合二为一来解决
     */
    @Sensitive(type = Sensitive.SensitiveType.PASSWORD)
    private String phone;


    private List<String> passwords;


    private Friend friend;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
