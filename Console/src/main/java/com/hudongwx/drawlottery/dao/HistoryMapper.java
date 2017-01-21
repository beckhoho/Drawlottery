package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.dto.paramBody.HistoryParam;
import com.hudongwx.drawlottery.pojo.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> selectList(@Param("param") HistoryParam param);

    List<String> selectRoundTimes(@Param("key") String key);

    /**
     * @param id 商品id
     * @return 详细信息
     */
    History selectHistory(long id);
}