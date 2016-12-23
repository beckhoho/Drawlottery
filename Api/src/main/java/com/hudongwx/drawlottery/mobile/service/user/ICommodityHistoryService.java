package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 16:07　<br/>
 * <p>
 *          历史商品service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityHistoryService {

    //添加商品到历史商品
    boolean addCommodHistory(CommodityHistory commodityHistory);

    //查看历史商品
    CommodityHistory selectHistoryCommod();
}
