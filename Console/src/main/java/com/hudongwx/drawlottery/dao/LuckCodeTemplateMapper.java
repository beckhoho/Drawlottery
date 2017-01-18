package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.LuckCodeTemplate;
import com.hudongwx.drawlottery.pojo.LuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface LuckCodeTemplateMapper extends BaseMapper<LuckCodeTemplate> {
    public long getCount();

    void insertSet(@Param("set") Set<LuckCodeTemplate> set);

    List<LuckCodes> selectRange(@Param("count") long count);
}