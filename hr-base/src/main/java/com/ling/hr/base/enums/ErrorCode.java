package com.ling.hr.base.enums;

import com.ling.hr.base.IErrorCode;

public enum ErrorCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");


    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
