package com.ling.hr.base.exception;

/**
 * 业务逻辑异常
 */
public class BusinessException extends BaseException {

    public BusinessException(Integer code) {
        super(code);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(Integer code, Throwable cause) {
        super(code, cause);
    }
}
