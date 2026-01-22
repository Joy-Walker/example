package com.example.vo;

import com.example.sensitive.Sensitive;
import com.example.sensitive.jackson.SensitiveByJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 14:02:00
 */
public class Friend {

    private String name;

    @Sensitive(type = Sensitive.SensitiveType.PHONE)
    private String phone;

    private List<String> passwords;

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
