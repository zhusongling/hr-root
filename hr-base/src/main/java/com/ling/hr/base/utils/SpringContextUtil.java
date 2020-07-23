package com.ling.hr.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (SpringContextUtil.class) {
            logger.debug("setApplicationContext, notifyAll");
            SpringContextUtil.applicationContext = applicationContext;
            SpringContextUtil.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        synchronized (SpringContextUtil.class) {
            while (applicationContext == null) {
                try {
                    logger.debug("getApplicationContext, wait...");
                    SpringContextUtil.class.wait(60000);
                    if (applicationContext == null) {
                        logger.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
                    }
                } catch (InterruptedException ex) {
                    logger.debug("getApplicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }

    /**
     * 根据名字获取实体
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类型获取实体
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据名字、类型获取实体
     *
     * @param <T>
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 根据类型获取实体
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }
}
