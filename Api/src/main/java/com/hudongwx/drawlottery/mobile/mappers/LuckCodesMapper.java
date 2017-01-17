package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {

    //通过商品名查询luckCode
    List<LuckCodes> selectByUsable(@Param("commodityId") Long commodityId);

    List<LuckCodes> selectLimit(@Param("commodityId") Long commodityId,@Param("endNum")Integer endNum);

    LuckCodes selectById(@Param("lcId") Long lcId);

    LuckCodes selectByCode(@Param("code") String code);

    int updateById(List<Long> list);
}