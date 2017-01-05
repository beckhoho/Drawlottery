package com.hudongwx.drawlottery.common.constants;

import com.hudongwx.drawlottery.common.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置常量类.
 * Date: 2017/1/5 0005
 * Time: 11:08
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ConfigConstants {
    private static Logger logger  = LoggerFactory.getLogger(ConfigConstants.class);

    /**
     * 获取properties配置文件属性
     */
    private static final String DEPLOY_PROPERTIES = "project/deploy.properties";


    static {
        PropertiesUtils.loadAllProperties(DEPLOY_PROPERTIES);
    }

    /**
     * 项目路径
     */
    private static final String LABEL_CONTEXT_PATH = "contextPath";
    public static final String contextPath = PropertiesUtils.getProperty(LABEL_CONTEXT_PATH);

    /**
     * 静态资源
     */
    private static final String LABEL_STATIC_SERVE_PATH = "staticServePath";
    public static final String staticServePath = PropertiesUtils.getProperty(LABEL_STATIC_SERVE_PATH);

    /**
     * shiro 管理地址
     */
    private static final String LABEL_LOGIN_URL = "loginUrl";
    public static final String loginUrl = PropertiesUtils.getProperty(LABEL_LOGIN_URL);

    private static final String LABEL_SUCCESS_URL = "successUrl";
    public static final String successUrl = PropertiesUtils.getProperty(LABEL_SUCCESS_URL);

    private static final String LABEL_UNAUTHORIZED_URL = "unauthorizedUrl";
    public static final String unauthorizedUrl = PropertiesUtils.getProperty(LABEL_UNAUTHORIZED_URL);

    private ConfigConstants() {
    }


}
