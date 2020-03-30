package com.javayh.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * <p>
 *      spring获取bean
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 23:03
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        checkApplicationContext();
        return applicationContext.getBean(name,clazz);
    }

    public static String getProperty(String key){
        return applicationContext.getBean(Environment.class).getProperty(key);
    }


    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在applicationContext.xml中定义SpringUtils");
        }
    }

}
