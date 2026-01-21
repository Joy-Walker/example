package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_TRAILING_COMMA, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED,true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
    }

    /**
     * 将Java对象转换为JSON字符串
     *
     * @param obj 要转换的Java对象
     * @return JSON字符串
     * @throws JsonProcessingException 如果转换过程中发生错误
     */
    public static String toJson(Object obj) {
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e) {
            LOGGER.error("failed to invoke to json",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将JSON字符串反序列化为指定类型的Java对象
     *
     * @param jsonStr JSON字符串
     * @param clazz   目标对象的类型
     * @param <T>     泛型类型参数
     * @return 反序列化后的Java对象
     * @throws JsonProcessingException 如果解析过程中发生错误
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz)  {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            LOGGER.error("failed to invoke fromJson exception",e);
            throw new RuntimeException(e);
        }
    }



    public static <T> T fromJsonByte(byte[] bytes, Class<T> clazz)  {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            LOGGER.error("failed to invoke fromJson exception",e);
            throw new RuntimeException(e);
        }
    }
}
