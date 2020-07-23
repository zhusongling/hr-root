package com.ling.hr.base.model;

import com.ling.hr.base.constant.BaseErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Result extends DataObject {

    private Integer code = BaseErrorCode.STATUS_SUCCESS; // 默认成功，非1即为失败
    private String message = "";
    private Object data = new Object();

    private Result() {

    }

    public static Result newInstance() {
        return new Result();
    }
}
