package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLuckCodesMapper extends BaseMapper<UserLuckCodes> {

    @Select("select * from t_user_luck_codes where user_account_id = #{accountId}")
    List<UserLuckCodes> selectByUserId(@Param("accountId") Long accountId);
}