package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;

import java.util.List;
import java.util.Map;

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
 * 历史商品service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityHistoryService {


//    /**
//     * 查询指定商品的往期揭晓
//     *
//     * @param commId
//     * @return
//     */
//    List<Map> selectThePastAnnouncedCommList(Long commId);

    /**
     * 查看该期商品中奖信息
     * @return
     */
    LotteryInfo selectLotteryInfo(Long commId);
}
