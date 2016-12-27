package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NotificationPrizeMapper extends BaseMapper<NotificationPrize> {


    @Select("SELECT * FROM t_notification_prize ORDER BY on_prize_date DESC")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column="user_account_id",property="accountId"),
            @Result(column="commodity_id",property="commodityId"),
            @Result(column="luck_codes_id",property="luckCodesId"),
            @Result(column="on_prize_date",property="onPrizeDate")
    })
    List<NotificationPrize> selectByNew();


}