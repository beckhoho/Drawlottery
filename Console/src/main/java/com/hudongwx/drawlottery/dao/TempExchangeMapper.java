package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.pojo.TempExchange;

import java.util.List;

public interface TempExchangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TempExchange record);

    int insertSelective(TempExchange record);

    TempExchange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TempExchange record);

    int updateByPrimaryKey(TempExchange record);

    int insertList(List<TempExchange> list);
}