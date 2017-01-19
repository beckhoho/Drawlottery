package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    /**
     * 查询上期商品开奖信息
     * @param commodName
     * @param roundTime
     * @return
     */
    List<CommodityHistory> selectByCommName(@Param("commodName") String commodName, @Param("roundTime") String roundTime);


    /**
     * 查询单件历史商品信息
     * @return  返回商品信息
     */
    CommodityHistory selectByCommId(@Param("commodityId") Long commodityId);

    /**
     * 查询中奖历史
     * @param accountId 用户ID
     * @return
     */
    List<CommodityHistory> selectHistoryLottery(@Param("accountId") Long accountId);

    CommodityHistory selectComIdAndUser(@Param("accountId")Long accountId,@Param("commodityId")Long commodityId);

    List<CommodityHistory> selectByTempId(@Param("tempId")Long tempId);

    int updateByCommodityIdSelective(@Param("commodityHistory") CommodityHistory commodityHistory);

    /**
     * 更改商品分享状态
     * @param commodityId
     * @return
     */
    int updateShareStateByCommodityId(@Param("commodityId")Long commodityId);
}