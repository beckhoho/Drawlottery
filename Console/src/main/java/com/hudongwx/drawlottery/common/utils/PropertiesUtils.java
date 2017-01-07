package com.hudongwx.drawlottery.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author CXX
 * @version 创建时间：2016年8月19日  上午11:17:51
 * @description： 读取配置通用处理工具类
 */
public class PropertiesUtils {
    private static Map<String, String> propertiesMap = new HashMap<>();
    // Default as in PropertyPlaceholderConfigurer
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    public static void processProperties(Properties props) throws BeansException {

        propertiesMap = new HashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();

            try {
                //PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadAllProperties(String fileName) {
        try {

            Properties properties = PropertiesLoaderUtils.loadAllProperties(fileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String name) {
        return propertiesMap.get(name);
    }

    public static int getPropToInt(String name) {
        return Integer.valueOf(propertiesMap.get(name));
    }
}
