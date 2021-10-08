package com.jerry.savior_common.defaultImpl;

import com.jerry.savior_common.interfaces.TokenExtractor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22454
 */
public class DefaultTokenExtractor implements TokenExtractor {
    public static final String HEADER_ATTRIBUTE_NAME = "auth";

    @Override
    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(HEADER_ATTRIBUTE_NAME);
    }
}
