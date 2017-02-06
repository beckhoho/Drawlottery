package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/15 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/15 16:20　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface CommodityMapper extends BaseMapper<Commodity> {

    //通过商品ID 查询商品模板ID
    Long selectTempIdByCommId(@Param("commId") Long commId);

    //通过商品ID 查询商品模板ID
    List<Long> selectCommIdByTempId(@Param("tempId") Long tempId,@Param("lastCommId") Long lastCommId,@Param("pageLoadSize")Integer pageLoadSize);

    //生成下一期（返回主键ID）
    int insertUseGenerated(Commodity commodity);

    //将开过奖的商品拷贝到商品表
    int insertHistory();

    //更新商品数据（字段非空处理）
    int updateById(Commodity commodity);

    //查询往期揭晓数据
    List<Long> selectBefore(@Param("tempId") Long tempId, @Param("commodityId") Long commodityId);

    /**
     * 查询上期商品开奖信息
     *
     * @param tempId
     * @param roundTime
     * @return
     */
    Commodity selectByTempIdOrderRoundTime(@Param("tempId") Long tempId, @Param("roundTime") String roundTime);
//    List<Long> selectCommodityBefore(@Param("tempId")Long tempId,@Param("roundTime")String roundTime);


    /**
     * 查询中奖历史
     *
     * @param accountId 用户ID
     * @return
     */
    List<Commodity> selectHistoryLottery(@Param("accountId") Long accountId);


    //查看用户单个中奖信息
    Commodity selectComIdAndUser(@Param("accountId") Long accountId, @Param("commodityId") Long commodityId);

    List<Commodity> selectByTempId(@Param("tempId") Long tempId);

    /**
     * 更改商品分享状态
     *
     * @param commodityId
     * @return
     */
    int updateShareStateByCommodityId(@Param("commodityId") Long commodityId, @Param("shareState") Integer shareState);

    /**
     * 查询往期商品中奖信息
     *
     * @param commodityId
     * @return
     */
    List<Map> selectPastLottery(Long commodityId);

    Commodity selectByKey(@Param("commodityId") Long commodityId);

    /**
     * 根据商品状态id 查询商品信息
     *
     * @return
     */
    List<Commodity> selectUnLotteryComm();

    long selectMaxRoundTime();

    Commodity selectNext(@Param("id") Long id);

    void updateState(@Param("id") Long id, @Param("state") int state);

    int deleteCommInfoByCommId(@Param("commId")Long commId);

    Integer updateViewNum(@Param("commId")Long commId,@Param("viewNum")Long viewNum);

    int updateBuyCurrentNum(@Param("id") Long id, @Param("buyCurrentNumber") int buyCurrentNumber);
}
