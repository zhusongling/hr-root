package com.ling.hr.base.annotation;

import com.ling.hr.base.enums.AuthType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AuthCheck {

    /**
     * 权限类型
     *
     * @return
     */
    AuthType value() default AuthType.NONE;

    /**
     * 权限类型
     *
     * @return
     */
    AuthType[] type() default AuthType.NONE;

    /**
     * 是否需要鉴权
     *
     * @return
     */
    boolean required() default false;
}
