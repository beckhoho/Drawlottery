package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommoditysMapper extends BaseMapper<Commoditys> {


    /**
     * 根据主键ID查询商品信息
     * @param commodityId   商品ID
     * @return
     */
    @Select("SELECT commodity.id,template.name,\n" +
            "   commodity.temp_id,"+
            "template.withdrawals_money,"+"template.exchange_money,"+
            "        commodity.view_num,template.commodity_desc,\n" +
            "        template.commodity_type_id,commodity.buy_current_number,\n" +
            "        template.buy_total_number,template.ground_time,\n" +
            "        commodity.luck_code_id,commodity.round_time,\n" +
            "        template.cover_img_url,template.auto_round,\n" +
            "        template.commodity_desc_url,commodity.buy_last_number,\n" +
            "        commodity.sell_out_time,commodity.state_id FROM t_commoditys\n" +
            "         as commodity LEFT JOIN t_commodity_template\n" +
            "          as template ON commodity.temp_id = template.id"+
            "   WHERE commodity.id = #{commodityId}")
    @Results({
            @Result(id = true , column = "id",property = "id"),
            @Result(column = "view_num",property = "viewNum"),
            @Result(column = "withdrawals_money",property = "withdrawalsMoney"),
            @Result(column = "exchange_money",property = "exchangeMoney"),
            @Result(column = "commodity_desc",property = "commodityDesc"),
            @Result(column = "commodity_type_id",property = "commodityTypeId"),
            @Result(column = "buy_current_number",property = "buyCurrentNumber"),
            @Result(column = "buy_total_number",property = "buyTotalNumber"),
            @Result(column = "ground_time",property = "groundTime"),
            @Result(column = "luck_code_id",property = "luckCodeId"),
            @Result(column = "round_time",property = "roundTime"),
            @Result(column = "cover_img_url",property = "coverImgUrl"),
            @Result(column = "auto_round",property = "autoRound"),
            @Result(column = "commodity_desc_url",property = "commodityDescUrl"),
            @Result(column = "buy_last_number",property = "byLastNumber"),
            @Result(column = "sell_out_time",property = "sellOutTime"),
            @Result(column = "state_id",property = "stateId"),
            @Result(column = "temp_id",property = "tempId")
    })
    Commoditys selectByKey(@Param("commodityId") Long commodityId);

    /**
     * 根据商品类型ID查询指定区域的商品信息
     * @param commodTypeId  商品类型ID
     * @param startNum     开始下标
     * @param endNum    结束下标
     * @return
     */
    List<Commoditys> selectPaging(@Param("commodTypeId") Integer commodTypeId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);


    /**
     * 查询当前商品类型商品总量
     * @param commodTypeId  商品类型ID
     * @return
     */
    @Select("select count(id) from t_commodity_template where commodity_type_id = #{commodTypeId} ")
    int selectTypeCount(@Param("commodTypeId") Integer commodTypeId);


    /**
     * 通过商品类型名查询商品类型ID
     * @param commodType  商品类型名
     * @return
     */
    @Select("select id from t_commodity_type where name = #{commodType} ")
    Long selectType(@Param("commodType") String commodType);


    //人气
    List<Commoditys> selectByTemp1();

    //最快
    List<Commoditys> selectByTemp2();

    //最新
    List<Commoditys> selectByTemp3();

    //高价
    List<Commoditys> selectByTemp4();

    /**
     * 查询开奖中的商品
     * @param maxInfo
     * @return
     */
    List<Commoditys> selectOnLottery(@Param("maxInfo") Integer maxInfo);

    List<Commoditys> selectTopTenOnLottery();

    /**
     * 通过商品类型ID查询商品
     * @param typeId
     * @param stateId
     * @return
     */
    List<Commoditys> selectByType(@Param("typeId") Integer typeId, @Param("stateId") Integer stateId);

    /**
     * 通过商品名模糊查询
     * @param name
     * @param stateId
     * @return
     */
    List<Commoditys> selectByName(@Param("name") String name, @Param("stateId") Integer stateId);

    /**
     * 确定商品类型，模糊查询商品
     * @param name
     * @param typeId
     * @param stateId
     * @return
     */
    List<Commoditys> selectByTypeAndName(@Param("name") String name, @Param("typeId") Integer typeId, @Param("stateId") Integer stateId);

    /**
     *  猜你喜欢商品
     * @return
     */
    List<Commoditys> selectByGuess();

    /**
     * 高中奖率商品
     * @param number
     * @return
     */
    List<Commoditys> selectHeight(@Param("number") Integer number);

    /**
     *  已开奖的商品
     * @return
     */
    List<Commoditys> selectHasTheLotteryComm();
}