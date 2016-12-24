package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserCodesHistoryMapper extends BaseMapper<UserCodesHistory> {

    @Select("select * from t_user_luck_codes_history where user_account_id = #{userAccountId}")
    List<UserCodesHistory> selectByUserId(@Param("userAccountId")Long accountId);

    @Select("select * from t_user_luck_codes_history where commodity_id = #{commodId}")
    List<UserCodesHistory> selectByCommodId(@Param("commodId")Long commodId);
}