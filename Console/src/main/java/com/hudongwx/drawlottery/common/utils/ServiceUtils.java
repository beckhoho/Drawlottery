package com.hudongwx.drawlottery.common.utils;

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
public class ServiceUtils {
    /**
     * 将集合处理成分页数据
     *
     * @param list 直接从数据库的获取到的list集合
     * @param page 查询的当前page
     * @param <T>  泛型
     * @return
     */
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
