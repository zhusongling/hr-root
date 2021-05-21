package com.ling.hr.base.model;

import com.ling.hr.base.IErrorCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WebResult<T> implements Serializable {
    private Integer code = 200;
    private String message = "操作成功";
    private T data;

    protected WebResult() {
    }

    /**
     * 成功返回结果
     *
     * @return
     */
    public static <T> WebResult<T> success() {
        return new WebResult<>();
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> WebResult<T> success(T data) {
        WebResult<T> result = new WebResult<>();
        result.data = data;
        return result;
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> WebResult<T> success(T data, String message) {
        WebResult<T> result = new WebResult<>();
        result.data = data;
        result.message = message;
        return result;
    }

    /**
     * 失败返回结果
     */
    public static <T> WebResult<T> fail() {
        return fail(500, "操作失败");
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> WebResult<T> fail(String message) {
        return fail(500, message);
    }

    /**
     * 失败返回结果
     *
     * @param code 错误码
     */
    public static <T> WebResult<T> fail(Integer code) {
        return fail(code, "操作失败");
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 提示信息
     */
    public static <T> WebResult<T> fail(Integer code, String message) {
        WebResult<T> result = new WebResult<T>();
        result.code = code;
        result.message = message;
        return result;
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> WebResult<T> fail(@NotNull IErrorCode errorCode) {
        WebResult<T> result = new WebResult<>();
        result.code = errorCode.getCode();
        result.message = errorCode.getMessage();
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
