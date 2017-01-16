package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    /**
     * 查询上期商品开奖信息
     * @param commodName
     * @param roundTime
     * @return
     */
    CommodityHistory selectBycommodName(@Param("commodName") String commodName, @Param("roundTime") String roundTime);


    /**
     * 查询单件历史商品信息
     * @param commodId  商品ID
     * @return  返回商品信息
     */
    CommodityHistory selectBycommId(@Param("cmmodId") Long commodId);

    /**
     * 查询中奖历史
     * @param accountId 用户ID
     * @return
     */
    List<CommodityHistory> selectHistoryLottery(@Param("accountId") Long accountId);

    List<CommodityHistory> selectComIdAndUser(@Param("accountId")Long accountId,@Param("commodityId")Long commodityId);

    int updateByIdSelective(@Param("commodityHistory") CommodityHistory commodityHistory);
}