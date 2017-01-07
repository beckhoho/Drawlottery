package com.hudongwx.drawlottery.common.constants;

import com.hudongwx.drawlottery.common.utils.PropertiesUtils;

/**
 * 语言常量.
 * Date: 2017/1/7 0007
 * Time: 17:36
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class LangConstants {

    /**
     * 获取properties配置文件属性
     */
    private static final String lang_PROPERTIES = "project/lang.properties";

    static {
        PropertiesUtils.loadAllProperties(lang_PROPERTIES);
    }

    private LangConstants() {
    }
}
