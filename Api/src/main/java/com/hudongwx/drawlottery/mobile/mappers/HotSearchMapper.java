package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.HotSearch;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 11:21　<br/>
 * <p>
 * 热门搜索实体类
 * <p>
 * @email 346905702@qq.com
 */
public interface HotSearchMapper extends BaseMapper<HotSearch> {

    //查询热门搜索关键词
    List<String> selectRecommend();

    //更新热门搜索关键词
    @Update("update t_hot_search set frequency = #{frequency} where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "frequency",property = "frequency")
    })
    int updateRecommend(@Param("id") Long id, @Param("frequency") Long frequency);

}
