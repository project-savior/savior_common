package com.jerry.savior_common.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jerry.savior_common.util.SensitiveDataConverter;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Documented
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = SensitiveDataConverter.class)
public @interface Desensitization {
    /**
     * 默认替换字符
     */
    char DEFAULT_REPLACE_CHARACTER = '*';

    /**
     * 脱敏策略
     */
    Strategy strategy() default Strategy.TOTAL;

    /**
     * 脱敏长度
     */
    int length() default 0;

    /**
     * 偏移长度
     */
    int offset() default 0;

    /**
     * 脱敏替换字符
     */
    char replaceChar() default DEFAULT_REPLACE_CHARACTER;

    enum Strategy {
        /**
         * 全部替换
         */
        TOTAL,
        /**
         * 从左边开始替换
         */
        START_ON_LEFT,
        /**
         * 从右边开始替换
         */
        START_ON_RIGHT
    }

}
