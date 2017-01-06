package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.CommodityType;

public interface CommodityTypeMapper extends BaseMapper<CommodityType> {
    public CommodityType selectByName(String name);
}