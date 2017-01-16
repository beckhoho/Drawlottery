package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.pojo.CommodityImages;
import com.hudongwx.drawlottery.pojo.CommodityImagesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityImagesMapper {
    int countByExample(CommodityImagesExample example);

    int deleteByExample(CommodityImagesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityImages record);

    int insertSelective(CommodityImages record);

    List<CommodityImages> selectByExample(CommodityImagesExample example);

    CommodityImages selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommodityImages record, @Param("example") CommodityImagesExample example);

    int updateByExample(@Param("record") CommodityImages record, @Param("example") CommodityImagesExample example);

    int updateByPrimaryKeySelective(CommodityImages record);

    int updateByPrimaryKey(CommodityImages record);

    int insertList(List<CommodityImages> imgs);
}