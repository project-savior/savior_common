package com.jerry.savior_common.asserts;


import com.jerry.savior_common.error.BusinessException;

public interface Assert {

    /**
     * create exception
     *
     * @param args args
     * @return exception
     */
    BusinessException newException(Object... args);

    /**
     * create exception
     *
     * @param t    throwable
     * @param args args
     * @return exception
     */
    BusinessException newException(Throwable t, Object... args);

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNull(Object obj) {
        if (obj == null) {
            throw newException((Object) null);
        }
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj  待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }
}
