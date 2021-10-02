package com.jerry.savior_common.constants;

import com.jerry.savior_common.asserts.BusinessExceptionAssert;
import lombok.AllArgsConstructor;

/**
 * @author 22454
 */
@AllArgsConstructor
public enum StandardResponse implements BusinessExceptionAssert {
    /**
     * 请求成功
     */
    OK(200, "ok"),
    /**
     * 禁止直接访问后端服务
     */
    FORBIDDEN(403, "access denied"),
    /**
     * 请求异常
     */
    ERROR(500, "业务异常,请稍后重试");
    private final Integer code;
    private final String message;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
