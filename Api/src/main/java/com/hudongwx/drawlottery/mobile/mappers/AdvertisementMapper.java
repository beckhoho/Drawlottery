package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementMapper extends BaseMapper<Advertisement> {

    List<Advertisement> selectAdByState(@Param("sta") Integer state);

}