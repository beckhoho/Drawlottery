package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NotificationPrizeMapper extends BaseMapper<NotificationPrize> {


    //查询中奖通知
    List<NotificationPrize> selectByNew();


}