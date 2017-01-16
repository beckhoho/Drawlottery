package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import org.apache.ibatis.annotations.Param;

public interface LuckCodeTemplateMapper extends BaseMapper<Approve> {

    /**
     * 按id查
     *
     * @param lctId
     * @return
     */
    LuckCodeTemplate selectById(@Param("lctId") Long lctId);
}