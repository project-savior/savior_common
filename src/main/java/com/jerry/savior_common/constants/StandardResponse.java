package com.jerry.savior_common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 22454
 */
@Getter
@AllArgsConstructor
public enum StandardResponse {
    /**
     * 请求成功
     */
    OK(200, "ok"),
    /**
     * 请求异常
     */
    ERROR(500, "业务异常,请稍后重试");
    private final Integer code;
    private final String message;
}
