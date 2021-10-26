package com.jerry.savior_common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.jerry.savior_common.annotations.Desensitization;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author 22454
 */
public class SensitiveDataConverter extends JsonSerializer<String> implements ContextualSerializer {
    private Desensitization desensitization;

    public SensitiveDataConverter() {
    }

    public SensitiveDataConverter(Desensitization desensitization) {
        this.desensitization = desensitization;
    }

    @Override
    public void serialize(String source, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (StringUtils.isBlank(source)) {
            jsonGenerator.writeString(source);
            return;
        }
        if (desensitization != null) {
            String result = replaceChar(source);
            jsonGenerator.writeString(result);
            return;
        }
        jsonGenerator.writeString(source);
    }

    private String replaceChar(String source) {
        // 策略
        Desensitization.Strategy strategy = desensitization.strategy();
        // 脱敏长度
        int length = desensitization.length();
        // 偏移量
        int offset = desensitization.offset();
        // 替换字符
        char replaceChar = desensitization.replaceChar();

        char[] chars = source.toCharArray();
        int charArrayLength = chars.length;
        String result;
        switch (strategy) {
            case START_ON_LEFT:
                result = replace(chars, offset, offset + length, replaceChar);
                break;
            case START_ON_RIGHT:
                result = replace(chars, charArrayLength - length - offset, charArrayLength - length, replaceChar);
                break;
            default:
                result = replace(chars, 0, charArrayLength, replaceChar);
                break;
        }
        return result;
    }

    private String replace(char[] chars, int start, int end, char replaceChar) {
        for (int i = start; i < end && i < chars.length; i++) {
            chars[i] = replaceChar;
        }
        return new String(chars);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        Desensitization annotation = beanProperty.getAnnotation(Desensitization.class);
        if (annotation != null) {
            return new SensitiveDataConverter(annotation);
        }
        return this;
    }
}
