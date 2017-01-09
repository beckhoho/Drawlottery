package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLuckCodesMapper extends BaseMapper<UserLuckCodes> {

    @Select("select distinct user_account_id from t_user_luck_codes")
    @Results({
            @Result(column = "user_account_id",property = "userAccountId")
    })
    List<UserLuckCodes> selectCountByCommodity(@Param("commod") Long commod);

    @Select("select distinct commodity_id from t_user_luck_codes WHERE user_account_id = #{accountId}")
    List<Long> selectDistinctGroupByCommId(@Param("accountId") Long accountId);
}