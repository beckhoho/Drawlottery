package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommoditysMapper extends BaseMapper<Commoditys> {


    /**
     * 根据主键ID查询商品信息
     *
     * @param commodityId 商品ID
     * @return
     */
    Commoditys selectByKey(@Param("commodityId") Long commodityId);

    /**
     * 根据主键ID查询商品信息
     *
     * @param commIdList 商品ID
     * @return
     */
    List<String> selectCommNameListByCommId(@Param("commIdList") List<Long> commIdList);

    /**
     * 根据主键ID查询商品信息
     *
     * @param commId 商品ID
     * @return
     */
    String selectCommNameByCommId(@Param("commIdList") Long commId);

    /**
     * 根据商品类型ID查询指定区域的商品信息
     *
     * @param commodTypeId 商品类型ID
     * @param startNum     开始下标
     * @param endNum       结束下标
     * @return
     */
    List<Commoditys> selectPaging(@Param("commodTypeId") Integer commodTypeId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);

    /**
     * 查询当前商品类型商品总量
     *
     * @param commodTypeId 商品类型ID
     * @return
     */
    @Select("select count(id) from t_commodity_template where commodity_type_id = #{commodTypeId} ")
    int selectTypeCount(@Param("commodTypeId") Integer commodTypeId);


    /**
     * 通过商品类型名查询商品类型ID
     *
     * @param commodType 商品类型名
     * @return
     */
    @Select("select id from t_commodity_type where name = #{commodType} ")
    Long selectType(@Param("commodType") String commodType);


    //人气
    List<Commoditys> selectByTemp1(@Param("lastCommId") Long lastCommId, @Param("maxInfoSize") Integer maxInfoSize);

    //最快
    List<Commoditys> selectByTemp2(@Param("lastCommId") Long lastCommId, @Param("maxInfoSize") Integer maxInfoSize);

    //最新
    List<Commoditys> selectByTemp3(@Param("lastCommId") Long lastCommId, @Param("maxInfoSize") Integer maxInfoSize);

    //高价
    List<Commoditys> selectByTemp4(@Param("lastCommId") Long lastCommId, @Param("maxInfoSize") Integer maxInfoSize);

    /**
     * 查询开奖中的商品
     *
     * @param maxInfo
     * @return
     */
    List<Commoditys> selectOnLottery(@Param("maxInfo") Integer maxInfo);

    List<Commoditys> selectAnnouncedComm(@Param("lastCommId") Long lastCommId,@Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 通过商品类型ID查询商品
     *
     * @param typeId
     * @param stateId
     * @return
     */
    List<Commoditys> selectByType(@Param("typeId") Integer typeId, @Param("stateId") Integer stateId, @Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 通过商品名模糊查询
     *
     * @param name
     * @param stateId
     * @return
     */
    List<Commoditys> selectByName(@Param("name") String name, @Param("stateId") Integer stateId, @Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 确定商品类型，模糊查询商品
     *
     * @param name
     * @param typeId
     * @param stateId
     * @return
     */
    List<Commoditys> selectByTypeAndName(@Param("name") String name, @Param("typeId") Integer typeId, @Param("stateId") Integer stateId, @Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 猜你喜欢商品
     *
     * @return
     */
    List<Commoditys> selectByGuess();

    /**
     * 高中奖率商品
     *
     * @param number
     * @return
     */
    List<Commoditys> selectHeight(@Param("number") Integer number, @Param("lastCommId") Long lastCommId, @Param("maxInfoSize") Integer maxInfoSize);

    /**
     * 已开奖的商品
     *
     * @return
     */
    List<Commoditys> selectHasTheLotteryComm();

    int updateCommState(@Param("commId") Long commId, @Param("stateId") Integer stateId);

    int updateById(Commodity commodity);

    Long selectMaxRoundTime();

    Commoditys selectNextRoundComm(@Param("tempId") Long tempId, @Param("stateId") Integer stateId);
}