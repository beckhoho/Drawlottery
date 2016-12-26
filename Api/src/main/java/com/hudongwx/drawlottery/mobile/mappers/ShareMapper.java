package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    List<Share> selectPageShareInfo(@Param("accountid") Long accountid, @Param("lastshareid") Long lastshareid, @Param("tag") Integer tag);
}