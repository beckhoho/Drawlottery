package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    /**
     * 更改商品分享状态
     *
     * @param commodityId
     * @return
     */
    int updateShareStateByCommodityId(@Param("commodityId") Long commodityId,@Param("shareState") Integer shareState);

    /**
     * 查询所有(查询已揭晓的商品)
     *
     * @return
     */
    List<CommodityHistory> selectAllWithPage(@Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);
}