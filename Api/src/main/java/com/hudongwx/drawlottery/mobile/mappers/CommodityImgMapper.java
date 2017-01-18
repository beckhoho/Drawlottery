package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityImgMapper extends BaseMapper<CommodityImg> {
    List<CommodityImg> selectByCommId(@Param("commId") Long commId);

    List<String> selectImgUrlListByTempId(@Param("tempId") Long tempId, @Param("state") Integer state);
}