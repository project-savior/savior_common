package com.jerry.savior_common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 22454
 */
@Slf4j
@Component
public class ObjectMapperHelper {
    private final ObjectMapper mapper;

    @Autowired
    public ObjectMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("序列化失败,原因：", e);
            return null;
        }
    }

    public <T> T parseJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.warn("反序列化失败，原因：", e);
            return null;
        }
    }

    public <T> T parseJson(String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (Exception e) {
            log.warn("反序列化失败，原因：", e);
            return null;
        }
    }
}
