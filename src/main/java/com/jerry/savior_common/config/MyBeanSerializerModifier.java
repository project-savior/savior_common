package com.jerry.savior_common.config;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author 22454
 */
public class MyBeanSerializerModifier extends BeanSerializerModifier {
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
            if (isArray(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(new CustomizeNullSerializer.NullArraySerializer());
            } else if (isString(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(new CustomizeNullSerializer.NullStringSerializer());
            } else if (isNumber(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(new CustomizeNullSerializer.NullNumberSerializer());
            } else if (isBoolean(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(new CustomizeNullSerializer.NullBooleanSerializer());
            } else if (isDate(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(new CustomizeNullSerializer.NullDateSerializer());
            }
        }
        return beanProperties;
    }

    /**
     * 是否数组类型
     *
     * @param writer writer
     * @return true/false
     */
    private boolean isArray(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return rawClass.isArray()
                || Collection.class.isAssignableFrom(rawClass);
    }


    /**
     * 是否字符串类型
     *
     * @param writer writer
     * @return true/false
     */
    private boolean isString(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(rawClass) || Character.class.isAssignableFrom(rawClass);
    }

    /**
     * 是否数值类型
     *
     * @param writer writer
     * @return true/false
     */
    private boolean isNumber(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(rawClass);
    }

    /**
     * 是否布尔类型
     *
     * @param writer writer
     * @return true/false
     */
    private boolean isBoolean(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return Boolean.class.isAssignableFrom(rawClass);
    }

    /**
     * 是否日期类型
     *
     * @param writer writer
     * @return true/false
     */
    private boolean isDate(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return Date.class.isAssignableFrom(rawClass);
    }
}
