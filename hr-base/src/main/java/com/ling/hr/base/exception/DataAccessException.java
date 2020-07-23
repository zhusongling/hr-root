package com.ling.hr.base.exception;

/**
 * 数据源异常
 */
public class DataAccessException extends BaseException {

    public DataAccessException(Integer code) {
        super(code);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(Integer code, Throwable cause) {
        super(code, cause);
    }
}
