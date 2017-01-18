package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.pojo.ExchangeWay;

public interface ExchangeWayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeWay record);

    int insertSelective(ExchangeWay record);

    ExchangeWay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeWay record);

    int updateByPrimaryKey(ExchangeWay record);
}