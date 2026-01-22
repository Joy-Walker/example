package com.example.sensitive;

import com.example.sensitive.jackson.SensitiveByJsonSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 09:56:00
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside // 核心：告诉Jackson，这个注解下面还藏着别的注解
@JsonSerialize(using = SensitiveByJsonSerializer.class)
public @interface Sensitive {

    SensitiveType type() default SensitiveType.NONE; // 脱敏类型：手机号、身份证、姓名等

    enum SensitiveType {
        NONE,
        PHONE,
        ID_CARD,
        NAME,
        ADDRESS,
        BANK_CARD,
        EMAIL,
        PASSWORD,
        OTHER
    }
}
