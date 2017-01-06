package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.CommodityUserHistory;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 11:25　<br/>
 * <p>
 *  用户商品对应历史service
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityUserHistoryService {

    boolean addCommodityUserHistory(CommodityUserHistory userHistory);

    boolean deleteUserHistory(CommodityUserHistory userHistory);

    boolean updateUserHistory(CommodityUserHistory userHistory);

    List<Map<String,Object>> selectUserHistory(Long accountId);

}
