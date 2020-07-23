package com.ling.hr.base.exception;

import com.ling.hr.base.constant.BaseErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常基类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {

    protected Integer code = BaseErrorCode.INTERNAL_SERVER_ERROR;

    public BaseException(Integer code) {
        super(String.valueOf(code));
        this.code = code;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(Integer code, Throwable cause) {
        super(String.valueOf(code), cause);
        this.code = code;
    }
}
