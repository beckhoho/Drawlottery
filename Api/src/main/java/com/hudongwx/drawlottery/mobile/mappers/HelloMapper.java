package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface HelloMapper extends BaseMapper<Users> {

    @Select("select * from users where name = #{name}")
    Users selectByName(@Param("name") String name);

    @Insert("insert into users(name,passwd) values (#{name},#{passwd})")
    int insertUser(@Param("name") String name, @Param("passwd") String passwd);

}
