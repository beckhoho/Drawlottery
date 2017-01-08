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
    public static String format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
