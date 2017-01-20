package com.hudongwx.drawlottery.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * spring bean 管理类.
 * Date: 2017/1/10 0018
 * Time: 10:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {
        return context.getBean(name, requiredType);
    }

    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return context.isSingleton(name);
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return context.getType(name);
    }

}
