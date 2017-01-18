package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.LuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {
    int insertAuto(LuckCodes code);

    int insertCodeList(@Param("list") List<LuckCodes> list);
}