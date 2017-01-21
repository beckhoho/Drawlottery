package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.pojo.Card;
import com.hudongwx.drawlottery.pojo.CardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    int countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    List<Card> selectByExample(CardExample example);

    Card selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    List<Card> selectAll(@Param("corporation") List<Integer> corporation, @Param("order") int order, @Param("direction") int direction);

    void delete(@Param("cardIds") List<Integer> cardIds);
}