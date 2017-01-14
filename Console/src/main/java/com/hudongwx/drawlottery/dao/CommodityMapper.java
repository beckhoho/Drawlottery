package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommodityMapper extends BaseMapper<Commodity> {
    public List<Commodity> selectCommodities(@Param("key") String key,
                                                     @Param("genre") List<Integer> genre,
                                                     @Param("type") List<Integer> type,
                                                     @Param("state") List<Integer> state,
                                                     @Param("groundTimeFront") Date groundTimeFront,
                                                     @Param("groundTimeAfter") Date groundTimeAfter,
                                                     @Param("undercarriageTimeFront") Date undercarriageTimeFront,
                                                     @Param("undercarriageTimeAfter") Date undercarriageTimeAfter,
                                                     @Param("order") int order, @Param("direction") int direction, @Param("valid") int valid);

    int batchDelete(@Param("commodityId") List<Integer> commodityId);

    /**
     * 更新销售状态，此处更新时，上架时间为null则不会更新上架时间，下架时间为不论是否为null都会更新为设置的值.
     *
     * @param ids        id集合
     * @param state      状态 定义在{@link Commodity}中
     * @param groundTime 上架时间
     * @param underTime  下架时间
     * @return 更新结果
     */
    public int updateState(@Param("ids") List<Integer> ids, @Param("state") int state, @Param("groundTime") Long groundTime, @Param("underTime") Long underTime);

    public List<String> selectNames(@Param("name") String name);

    public long selectMaxRoundTime();

    public int insertCommodity(Commodity commodity);

    public int checkState(@Param("ids") List<Integer> ids, @Param("state") int state);
}