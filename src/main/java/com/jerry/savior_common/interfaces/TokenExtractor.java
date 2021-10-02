package com.jerry.savior_common.interfaces;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22454
 */
public interface TokenExtractor {
    /**
     * 从request获取token
     *
     * @param request request
     * @return token
     */
    String getTokenFromRequest(HttpServletRequest request);
}
