package com.ling.hr.base.init;

import com.ling.hr.base.utils.I18NUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class I18NConfigInitializer implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.logger.info("初始化I18N国际化信息开始…START…");

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] configLocations = resolver.getResources("classpath*:i18n/*.properties");
        for (Resource configLocation : configLocations) {
            String fileName = configLocation.getFilename();
            String lang = fileName.substring(0, fileName.lastIndexOf("."));
            this.logger.debug("语言: " + lang);

            InputStream is = configLocation.getInputStream();
            Properties props = new Properties();
            props.load(is);
            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);
                I18NUtil.put(lang, key, value);
            }
        }

        this.logger.info("初始化I18N国际化信息结束…END…");
    }
}
