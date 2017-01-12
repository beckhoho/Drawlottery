package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.CommodityUserHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 11:08　<br/>
 * <p>
 *          商品用户历史对应service
 * <p>
 * @email 346905702@qq.com
 */
@Service
public interface CommodityUserHistoryMapper extends BaseMapper<CommodityUserHistory>{

    /**
     * 查询购买历史
     * @param accountId 用户ID
     * @return  返回历史商品信息集合
     */
    List<CommodityUserHistory> selectHistoryPay(@Param("accountId") Long accountId);
}
