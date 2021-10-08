package com.jerry.savior_common.error;


import com.jerry.savior_common.interfaces.IResponseEnum;

import java.text.MessageFormat;


/**
 * @author 22454
 */
public class BaseException extends RuntimeException implements IResponseEnum {
    private final Integer code;
    private final String message;
    private final IResponseEnum responseEnum;

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message == null ? responseEnum.getMessage() : message);
        this.responseEnum = responseEnum;
        this.code = responseEnum.getCode();
        this.message = MessageFormat.format(responseEnum.getMessage(), args);
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public IResponseEnum getResponseEnum() {
        return this.responseEnum;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", responseEnum=" + responseEnum +
                '}';
    }
}
