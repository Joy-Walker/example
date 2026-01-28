package com.example.sensitive.jackson;

import com.example.sensitive.DesensitizedUtil;
import com.example.sensitive.Sensitive;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 09:57:00
 */
public class SensitiveByJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Sensitive.SensitiveType type;

    // 默认构造，Jackson 初始扫描用
    public SensitiveByJsonSerializer() {
        this.type = null;
    }

    // 供业务使用
    public SensitiveByJsonSerializer(Sensitive.SensitiveType type) {
        this.type = type;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // 针对直接object.write("aaa") 这样的情况处理
        if(beanProperty == null) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        Annotation annotation = beanProperty.getAnnotation(Sensitive.class);
        if(annotation != null) {
            Sensitive.SensitiveType type = ((Sensitive) annotation).type();
            return new SensitiveByJsonSerializer(type);
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DesensitizedUtil.desensitize(value, type));
    }
}
