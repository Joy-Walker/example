package com.example.configuration;

import com.example.sensitive.jackson.SensitiveByJsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//@Configuration
public class JacksonConfig {
//    @Bean

    /**
     * 不建议使用这个方法，因为这个方法会覆盖掉默认的 ObjectMapper
     * @param builder
     * @return
     */
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // 注册全局 String 序列化器
        // 只要是 String 字段，Jackson 都会进我们的 createContextual 瞄一眼
        module.addSerializer(String.class, new SensitiveByJsonSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}