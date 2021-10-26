package com.jerry.savior_common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author 22454
 */
public class CustomizeNullSerializer {
    /**
     * 数组类型 null 序列化器
     */
    public static class NullArraySerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeEndArray();
        }
    }

    /**
     * 字符串类型 null 序列化器
     */
    public static class NullStringSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString("");
        }
    }

    /**
     * 数值类型 null 序列化器
     */
    public static class NullNumberSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(0);
        }
    }

    /**
     * 布尔类型 null 序列化器
     */
    public static class NullBooleanSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeBoolean(false);
        }
    }

    /**
     * object null 序列化器
     */
    public static class NullObjectSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeEndObject();
        }
    }

    /**
     * date null 序列化器
     */
    public static class NullDateSerializer extends JsonSerializer<Object>{
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString("1000-01-01 00:00:00");
        }
    }
}
