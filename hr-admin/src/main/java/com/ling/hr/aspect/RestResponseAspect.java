package com.ling.hr.aspect;

import com.ling.hr.base.annotation.RestResponse;
import com.ling.hr.base.exception.BaseException;
import com.ling.hr.base.exception.BusinessException;
import com.ling.hr.base.exception.DataAccessException;
import com.ling.hr.base.model.Result;
import com.ling.hr.base.utils.FastJsonUtil;
import com.ling.hr.base.utils.I18NUtil;
import com.ling.hr.base.utils.RequestContextUtil;
import com.ling.hr.constant.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 控制器返回结果处理切面
 */
@Aspect
@Component
public class RestResponseAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controllerInvocation() {

    }

    @Around("controllerInvocation()")
    public Object aroundController(ProceedingJoinPoint joinPoint) {
        Result result = Result.newInstance();
        try {
            Object[] args = joinPoint.getArgs();
            try {
                this.logger.info(String.format("请求参数[%s]", FastJsonUtil.toJSONString(args)));
            } catch (Exception e) {
            }
            Object retVal = joinPoint.proceed(args);
            try {
                this.logger.info(String.format("响应数据[%s]", FastJsonUtil.toJSONString(retVal)));
            } catch (Exception e) {
            }
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            if (targetMethod.isAnnotationPresent(RestResponse.class)) {
                RestResponse restResponse = targetMethod.getAnnotation(RestResponse.class);
                boolean value = restResponse.value();
                if (!value) {
                    return retVal;
                }
            }
            if (retVal != null) {
                if (retVal instanceof Result) {
                    result = (Result) retVal;
                } else {
                    result.setData(retVal);
                }
            }

        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);
            Integer code = ErrorCode.INTERNAL_SERVER_ERROR;
            if (cause instanceof BaseException) {
                BaseException exception = (BaseException) cause;
                code = exception.getCode();
                if (code == null) {
                    if (cause instanceof DataAccessException) {
                        code = ErrorCode.DATA_ACCESS_ERROR;
                    } else if (cause instanceof BusinessException) {
                        code = ErrorCode.BUSINESS_ERROR;
                    }
                }
            } else {
                this.logger.error(cause.getMessage());
            }
            result.setCode(code);
            String message = I18NUtil.get("ERROR_CODE_" + code, "zh_CN");
            result.setMessage(message);
        }
        RequestContextUtil.write(result);
        return null;
    }
}
