package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {

    //通过商品名查询luckCode
    List<LuckCodes> selectByUsable(@Param("commodityId") Long commodityId);

    //String selectLuckCode(@Param("luckCodeId") Long luckCodeId);
}