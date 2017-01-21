package com.hudongwx.drawlottery.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Drawlottery.
 * Date: 2017/1/9 0009
 * Time: 1:48
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class DateUtils {
    /**
     * 格式化时间戳
     *
     * @param date 时间对象
     * @return 时间戳字符串
     */
    public static String format(Date date) {
        if (null == date)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 得到当前日期字符串（纯数字格式,如2017-01-01）
     *
     * @return 日期字符字符串
     */
    public static String getNowDayNumber() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
}
