package com.jerry.savior_common.asserts;


import com.jerry.savior_common.error.BusinessException;
import com.jerry.savior_common.interfaces.IResponseEnum;

import java.text.MessageFormat;

/**
 * @author 22454
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BusinessException newException(Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, message);
    }

    @Override
    default BusinessException newException(Throwable t, Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, message);
    }
}
