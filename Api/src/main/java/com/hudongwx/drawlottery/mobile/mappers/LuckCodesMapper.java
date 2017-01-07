package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Select;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {

    @Select("select count(id) from t_luck_codes")
    Long selectCountMember();

}