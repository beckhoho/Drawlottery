package com.hudongwx.drawlottery.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author CXX
 * @version 创建时间：2016年8月19日  上午11:17:51
 * @description： 读取配置通用处理工具类
 */
public class PropertiesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);
    private  Properties properties = new Properties();

    public  Properties getProperties() {
        return properties;
    }

    public  void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * 读取配置文件
     *
     * @param fileName
     */
    public boolean readProperties(String fileName) {
        try {
            InputStream in = PropertiesUtils.class.getResourceAsStream("/" + fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            properties.load(bf);
        } catch (IOException e) {
            LOGGER.error("PropertiesUtils.readProperties:{}", e);
            return false;
        }
        return true;
    }

    /**
     * 根据key读取对应的value
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
