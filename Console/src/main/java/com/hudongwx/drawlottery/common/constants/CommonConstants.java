package com.hudongwx.drawlottery.common.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通用常量类.
 * Date: 2017/1/5 0005
 * Time: 11:12
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(locations = {"classpath:project/constants.properties"})
public class CommonConstants {

    /**
     * 显示/隐藏.
     */
    private static final String SHOW = "1";
    private static final String HIDE = "0";

    /**
     * 是/否.
     */
    private static final String YES = "1";
    private static final String NO = "0";


    /**
     * 对/错.
     */
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    /**
     * 可用/不可用.
     */
    private static final int VALID = 1;
    private static final int INVALID = 0;
    /**
     * 默认分页行数
     */
    private int maxPageSize;

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public String getSHOW() {
        return SHOW;
    }

    public String getHIDE() {
        return HIDE;
    }

    public String getYES() {
        return YES;
    }

    public String getNO() {
        return NO;
    }

    public static String getTRUE() {
        return TRUE;
    }

    public String getFALSE() {
        return FALSE;
    }

    public int getVALID() {
        return VALID;
    }

    public int getINVALID() {
        return INVALID;
    }
}
