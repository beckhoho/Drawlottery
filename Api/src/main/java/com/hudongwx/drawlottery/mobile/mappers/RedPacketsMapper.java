package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RedPacketsMapper extends BaseMapper<RedPackets> {

    @Select("select * from t_red_packets where user_account_id = #{accountId}")
    List<RedPackets> selectUserAll(@Param("accountId") Long accountId);
}