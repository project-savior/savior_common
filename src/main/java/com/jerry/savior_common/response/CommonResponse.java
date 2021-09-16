package com.jerry.savior_common.response;


import com.jerry.savior_common.constants.CommonResponseCode;

import java.io.Serializable;

/**
 * @author 22454
 */
public final class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.create(data, "success");
    }

    public static <T> CommonResponse<T> success() {
        return CommonResponse.create(null, "success");
    }

    public static <T> CommonResponse<T> create(T data, String message) {
        return CommonResponse.create(CommonResponseCode.SUCCESS_CODE, data, message);
    }

    public static <T> CommonResponse<T> create(Integer code, T data, String message) {
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        return commonResponse;
    }

    public boolean isSuccess() {
        return CommonResponseCode.SUCCESS_CODE.equals(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
