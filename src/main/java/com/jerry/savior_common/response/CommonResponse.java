package com.jerry.savior_common.response;


import com.jerry.savior_common.constants.StandardResponse;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author 22454
 */
@Data
public final class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> build() {
        return CommonResponse.build(null, StandardResponse.OK.getMessage());
    }

    public static <T> CommonResponse<T> build(T data) {
        return CommonResponse.build(data, StandardResponse.OK.getMessage());
    }


    public static <T> CommonResponse<T> build(T data, String message) {
        return CommonResponse.build(StandardResponse.OK.getCode(), data, message);
    }

    public static <T> CommonResponse<T> build(Integer code, T data, String message) {
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        return commonResponse;
    }

    public boolean isSuccess() {
        return StandardResponse.OK.getCode().equals(code);
    }
}
