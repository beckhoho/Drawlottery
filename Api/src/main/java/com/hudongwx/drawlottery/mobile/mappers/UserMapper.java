package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_users where phone_number = #{phone}")
    @Results({
            @Result(id = true, column = "account_id",property = "accountId"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "user_integral", property = "userIntegral"),
            @Result(column = "current_state", property = "currentState"),
            @Result(column = "header_url", property = "headerUrl"),
            @Result(column = "gold_number", property = "goldNumber"),
    })
    User selectByPhoneNumber(@Param("phone") String phone);
}