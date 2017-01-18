package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationPrizeMapper extends BaseMapper<NotificationPrize> {


    //查询中奖通知
    List<NotificationPrize> selectByNew();

    //查询用户未读信息条数
    Integer countUnreadMsg(@Param("UserId") Long UserId);

    //查询往期信息
    List<NotificationPrize> selectLimitTen(@Param("UserId") Long UserId,@Param("msgId") String msgId);

    //更改消息阅读状态
    Integer updateStateById(@Param("Id")Long Id);
}