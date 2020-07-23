package com.ling.hr.base.aspect;

import com.ling.hr.base.constant.AppConstants;
import com.ling.hr.base.enums.RowStatus;
import com.ling.hr.base.model.DataEntity;
import com.ling.hr.base.model.UserObject;
import com.ling.hr.base.utils.ContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * MyBatis操作数据库时，初始化公共字段
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = false)
@Component
public class DataSourceAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.ling.*.mapper.*.*(..))")
    private void doPointcut() {
    }

    @Around("doPointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        try {
            for (Object arg : args) {
                if (arg instanceof List) {
                    List list = (List) arg;
                    for (int i = 0; i < list.size(); i++) {
                        this.initData(list.get(i));
                    }
                } else {
                    this.initData(arg);
                }

            }
        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);
        }
        return joinPoint.proceed(args);
    }

    private void initData(Object arg) {
        UserObject currentUser = ContextUtil.getCurrentUser();
        String userId = AppConstants.DEFAULT_USER;
        String appInstId = AppConstants.APP_INST_ID;
        if (currentUser != null) {
            userId = currentUser.getUserId();
        }
        if (arg instanceof DataEntity) {
            DataEntity entity = (DataEntity) arg;
            if (entity.getRowStatus() == null) {
                entity.setRowStatus(RowStatus.VALID.getValue());
            }
            entity.setRowCreator(userId);
            entity.setRowCreateInstId(appInstId);
            entity.setRowUpdater(userId);
            entity.setRowUpdateInstId(appInstId);
        } else if (arg instanceof Map) {
            Map entity = (Map) arg;
            if (!entity.containsKey(AppConstants.ROW_STATUS) || entity.get(AppConstants.ROW_STATUS) == null) {
                entity.put(AppConstants.ROW_STATUS, RowStatus.VALID.getValue());
            }
            entity.put(AppConstants.ROW_CREATOR, userId);
            entity.put(AppConstants.ROW_CREATE_INST_ID, appInstId);
            entity.put(AppConstants.ROW_UPDATER, userId);
            entity.put(AppConstants.ROW_UPDATE_INST_ID, appInstId);
        }
    }


}
