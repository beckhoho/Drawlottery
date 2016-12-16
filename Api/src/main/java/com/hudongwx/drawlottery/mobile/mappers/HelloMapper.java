package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface HelloMapper extends BaseMapper<User> {

    @Select("select * from users where name = #{name}")
    User selectByName(@Param("name") String name);

    @Insert("insert into users(name,passwd) values (#{name},#{passwd})")
    int insertUser(@Param("name")String name,@Param("passwd") String passwd);

}
