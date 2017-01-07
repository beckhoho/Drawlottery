package com.hudongwx.drawlottery.mobile.service.commodity;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 11:25　<br/>
 * <p>
 * 热门搜索service
 * <p>
 * @email 346905702@qq.com
 */
public interface IHotSearchService {

    /**
     * 添加热门搜索关键词
     *
     * @param keywords
     * @return
     */
    boolean addHotSearch(String keywords);

    /**
     * 获取热门推荐
     *
     * @return
     */
    List<String> queryRecommend();
}
