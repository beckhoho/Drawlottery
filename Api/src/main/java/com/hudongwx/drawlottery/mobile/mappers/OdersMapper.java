package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Oders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OdersMapper extends BaseMapper<Oders> {

    @Select("select * from t_oders where user_account_id = #{userAccount}")
    List<Oders> selectByUserAccount(@Param("userAccount") Long userAccount);

}