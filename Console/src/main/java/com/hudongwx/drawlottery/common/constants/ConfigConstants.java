package com.hudongwx.drawlottery.common.constants;

import com.hudongwx.drawlottery.common.utils.PropertiesUtils;

/**
 * 配置常量类.
 * Date: 2017/1/5 0005
 * Time: 11:08
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ConfigConstants {


    /**
     * 获取properties配置文件属性
     */
    private static final String DEPLOY_PROPERTIES = "properties/deploy.properties";

    private static PropertiesUtils propertiesUtils = new PropertiesUtils();

    public static boolean load() {
        return propertiesUtils.readProperties(DEPLOY_PROPERTIES);
    }

    /**
     * 项目路径
     */
    private static final String LABEL_CONTEXT_PATH = "contextPath";
    public static final String contextPath = propertiesUtils.getProperty(LABEL_CONTEXT_PATH);

    /**
     * 静态资源
     */
    private static final String LABEL_STATIC_SERVE_PATH = "staticServePath";
    public static final String staticServePath = propertiesUtils.getProperty(LABEL_STATIC_SERVE_PATH);

    /**
     * shiro 管理地址
     */
    private static final String LABEL_LOGIN_URL = "loginUrl";
    public static final String loginUrl = propertiesUtils.getProperty(LABEL_LOGIN_URL);

    private static final String LABEL_SUCCESS_URL = "successUrl";
    public static final String successUrl = propertiesUtils.getProperty(LABEL_SUCCESS_URL);

    private static final String LABEL_UNAUTHORIZED_URL = "unauthorizedUrl";
    public static final String unauthorizedUrl = propertiesUtils.getProperty(LABEL_UNAUTHORIZED_URL);

    private ConfigConstants() {
    }


}
