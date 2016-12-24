package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {

    @Select("select * from t_luck_codes where id = #{commodId}")
    List<LuckCodes> selectCommodCodes(@Param("commodId") Long commodId);
}