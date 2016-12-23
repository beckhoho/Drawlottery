package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CommodityTypeMapper extends BaseMapper<CommodityType> {

    //按类型名查询类型id, 参数(String name) 返回值类型 CommodityType;
    @Select("select * from t_commodity_type where name = #{typeName}")
    CommodityType selectType(@Param("typeName") String typeName);
}