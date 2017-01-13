package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;

public interface CommodityTemplateMapper extends BaseMapper<CommodityTemplate> {
    public int insertAuto(CommodityTemplate temp);
}