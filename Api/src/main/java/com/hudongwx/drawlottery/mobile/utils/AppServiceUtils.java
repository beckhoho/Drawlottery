package com.hudongwx.drawlottery.mobile.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * service工具类
 * <p>
 * @email 294786949@qq.com
 */
public class AppServiceUtils {
    public static <T extends Object> List<T> getPageList(List<T> list, Integer page) {
        List<T> newList = new ArrayList<>();
        if (null == page || page <= 0)
            return newList;
        int startPosition = Settings.PAGE_LOAD_SIZE * (page - 1);
        int endPosition = Settings.PAGE_LOAD_SIZE + startPosition;
        int maxPosition = endPosition >= list.size() ? list.size() : endPosition;
        for (int i = startPosition; i < maxPosition; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
}
