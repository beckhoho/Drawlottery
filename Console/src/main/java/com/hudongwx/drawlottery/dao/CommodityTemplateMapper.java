package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityTemplateMapper extends BaseMapper<CommodityTemplate> {
    public int insertAuto(CommodityTemplate temp);


    public List<CommodityTemplate> selectTemps(@Param("types") List<Integer> type, @Param("genres") List<Integer> genre, @Param("order") int order, @Param("direction") int direction, @Param("valid") int valid);

    public int updateState(@Param("ids") List<Integer> ids, @Param("state") int state, @Param("groundTime") Long groundTime, @Param("underTime") Long underTime);

    List<CommodityTemplate> selectByIds(@Param("ids") List<Integer> ids);
}