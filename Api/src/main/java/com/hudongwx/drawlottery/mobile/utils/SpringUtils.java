package com.hudongwx.drawlottery.mobile.utils;

import com.mysql.jdbc.Security;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * DATE:2016-12-2016/12/16 0016-09:34
 * Author: origin
 * DESC:
 * 工具类,用于获取spring里面的bean实例对象
 *
 */
@Component
public class SpringUtils  implements ApplicationContextAware{

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

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return context.isSingleton(name);
    }

    public static <T> T getBean(Class<T> requiredType)
            throws BeansException {
        return context.getBean(requiredType);
    }

    public static Class<? extends Object> getType(String name)
            throws NoSuchBeanDefinitionException {
        return context.getType(name);
    }

}
