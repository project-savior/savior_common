package com.jerry.savior_common.error;


import com.jerry.savior_common.asserts.Assert;
import com.jerry.savior_common.interfaces.IResponseEnum;

import java.text.MessageFormat;

/**
 * @author 22454
 */
public class BusinessException extends BaseException implements Assert {
    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum, null, null);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    @Override
    public BusinessException newException(Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, message);
    }

    @Override
    public BusinessException newException(Throwable t, Object... args) {
        return null;
    }

}
