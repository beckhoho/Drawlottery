package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    List<Long> selectCommodityBefore(@Param("tempId") Long tempId, @Param("roundTime") String roundTime);

    /**
     * 查询单件历史商品信息
     *
     * @return 返回商品信息
     */
    CommodityHistory selectByCommId(@Param("commodityId") Long commodityId);

    /**
     * 查询中奖历史
     *
     * @param accountId 用户ID
     * @return
     */
    List<CommodityHistory> selectHistoryLottery(@Param("accountId") Long accountId);

    CommodityHistory selectComIdAndUser(@Param("accountId") Long accountId, @Param("commodityId") Long commodityId);

    List<CommodityHistory> selectByTempId(@Param("tempId") Long tempId);

    int updateByCommodityIdSelective(@Param("commodityHistory") CommodityHistory commodityHistory);

    /**
     * 更改商品分享状态
     *
     * @param commodityId
     * @return
     */
    int updateShareStateByCommodityId(@Param("commodityId") Long commodityId);

    /**
     * 查询所有(查询已揭晓的商品)
     *
     * @return
     */
    List<CommodityHistory> selectAllWithPage(@Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);
}