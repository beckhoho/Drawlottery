package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface LuckCodeTemplateMapper extends BaseMapper<Approve> {

    /**
     * 按id查
     *
     * @param lctId
     * @return
     */
    LuckCodeTemplate selectById(@Param("lctId") Long lctId);


    LuckCodeTemplate selectByCode(@Param("code") String code);

    /**
     * 查询总数
     *
     * @return 总数
     */
    Long getCount();

    void insertSet(@Param("set") Set<LuckCodeTemplate> set);

    /**
     * 注意是赋予了模板id
     * @param count 总数
     * @return
     */
    List<LuckCodes> selectRange(long count);
}