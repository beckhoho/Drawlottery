package com.hudongwx.drawlottery.common.constants;

import com.hudongwx.drawlottery.common.utils.PropertiesUtils;

/**
 * 通用常量类.
 * Date: 2017/1/5 0005
 * Time: 11:12
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class CommonConstants {

    /**
     * 获取常量配置配置文件属性
     */
    private static final String CONSTANTS_PROPERTIES = "project/constants.properties";

    static {
        PropertiesUtils.loadAllProperties(CONSTANTS_PROPERTIES);
    }

    /**
     * 显示/隐藏.
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    /**
     * 是/否.
     */
    public static final String YES = "1";
    public static final String NO = "0";


    /**
     * 对/错.
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 可用/不可用.
     */
    public static final String VALID = "1";
    public static final String INVALID = "0";

    private CommonConstants() {
    }
}
