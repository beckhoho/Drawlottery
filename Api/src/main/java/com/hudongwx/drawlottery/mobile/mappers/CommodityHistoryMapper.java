package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommodityHistoryMapper extends BaseMapper<CommodityHistory> {

    @Select("select * from t_commodity_history where commodity_name = #{commodName} and round_time < #{roundTime}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "commodity_name",property = "commodityName"),
            @Result(column="commodity_id",property="commodityId"),
            @Result(column="luck_code",property="luckCode"),
            @Result(column="luck_user_account_id",property="luckUserAccountId"),
            @Result(column="round_time",property="roundTime"),
            @Result(column = "end_time",property = "endTime")
    })
    CommodityHistory selectBycommodId(@Param("commodName") String commodName,@Param("roundTime") Long roundTime);


    @Select("select * from t_commodity_history where commodity_id = #{cmmodId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "commodity_name",property = "commodityName"),
            @Result(column="commodity_id",property="commodityId"),
            @Result(column="luck_code",property="luckCode"),
            @Result(column="luck_user_account_id",property="luckUserAccountId"),
            @Result(column="round_time",property="roundTime"),
            @Result(column = "end_time",property = "endTime")
    })
    CommodityHistory selectBycommod(@Param("cmmodId") Long commodId);

    @Select("select id,commodity_name,luck_code,round_time,buy_number,end_time from t_commodity_history where luck_user_account_id = #{accountId}")
    @Results({
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "commodity_name",property = "commodityName"),
            @Result(column = "luck_code",property = "luckCode"),
            @Result(column = "round_time",property = "roundTime"),
            @Result(column = "buy_number",property = "buyNumber"),
            @Result(column = "end_time",property = "endTime")
    })
    List<CommodityHistory> selectHistoryLottery(@Param("accountId") Long accountId);

    @Select("select id,commodity_name,luck_code,round_time,buy_number,end_time from t_commodity_history where luck_user_account_id = #{accountId}")
    @Results({
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "commodity_name",property = "commodityName"),
            @Result(column = "luck_code",property = "luckCode"),
            @Result(column = "round_time",property = "roundTime"),
            @Result(column = "buy_number",property = "buyNumber"),
            @Result(column = "end_time",property = "endTime")
    })
    List<CommodityHistory> selectHistoryPay(@Param("accountId") Long accountId);
}