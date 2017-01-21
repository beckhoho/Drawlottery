package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    /**
     * 更改商品分享状态
     * @param commodityId
     * @return
     */
    int updateShareStateByCommodityId(@Param("commodityId")Long commodityId);
}