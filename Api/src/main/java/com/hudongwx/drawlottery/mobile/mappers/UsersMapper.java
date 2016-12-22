package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public interface UsersMapper extends BaseMapper<Users> {
    @Select("select * from t_users where account_id = #{acc}")
    Users selectByAccountId(@Param("acc") Integer acc);

    @Select("select * from t_users where account_id = #{acc} and password = #{pwd}")
    Users selectByAccount(@Param("acc") Integer acc, @Param("pwd") String pwd);
}
