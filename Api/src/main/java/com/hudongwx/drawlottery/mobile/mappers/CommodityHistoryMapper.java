package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    @Select("select (id,commodity_id,luck_code_id,user_account_id,round_time) from t_commodity_history where commodity_id = #{cmmodId} and round_time < #{roundTime}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column="commodity_id",property="commodityId"),
            @Result(column="luck_code",property="luckCode"),
            @Result(column="user_account_id",property="userAccountId"),
            @Result(column="round_time",property="roundTime"),
            @Result(column = "end_time",property = "endTime")
    })
    CommodityHistory selectBycommodId(@Param("cmmodId") Long cmmodId,@Param("roundTime") String roundTime);
}