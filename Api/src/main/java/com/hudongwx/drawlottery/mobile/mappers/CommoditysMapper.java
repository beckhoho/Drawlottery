package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommoditysMapper extends BaseMapper<Commoditys> {

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE commodity.id = #{commodityId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "ground_time", property = "groundTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    Commoditys selectByKey(@Param("commodityId") Long commodityId);

    //查询指定的区域数据
    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE commodity_type_id = #{commodTypeId}  limit #{startNum},#{endNum}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "ground_time", property = "groundTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectPaging(@Param("commodTypeId") Integer commodTypeId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);


    @Select("select count(id) from t_commodity_template where commodity_type_id = #{commodTypeId} ")
    int selectTypeCount(@Param("commodTypeId") Integer commodTypeId);


    @Select("select id from t_commodity_type where name = #{commodType} ")
    Long selectType(@Param("commodType") String commodType);

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE commodity.state_id = 3 ORDER BY" +
            " commodity.buy_current_number/template.buy_total_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "ground_time", property = "groundTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectByTemp1();

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE commodity.state_id = 3 ORDER BY\n" +
            "commodity.buy_current_number-commodity.buy_last_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "ground_time", property = "groundTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectByTemp2();

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id where commodity.state_id = 3 ORDER BY ground_time DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "ground_time", property = "groundTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectByTemp3();

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id where commodity.state_id = 3 ORDER BY template.buy_total_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectByTemp4();

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE template.buy_total_number - commodity.buy_current_number = 0 AND commodity.state_id = 2 order by commodity.sell_out_time desc limit #{maxInfo}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectOnLottery(@Param("maxInfo") Integer maxInfo);

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id WHERE template.buy_total_number - commodity.buy_current_number = 0 AND commodity.state_id=2 order by commodity.sell_out_time desc limit 10")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectTopTenOnLottery();


    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,\n" +
            "commodity.round_time,template.cover_img_url,\n" +
            "commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id  " +
            "where template.commodity_type_id  =  #{typeId} and commodity.state_id = #{stateId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "round_time",property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectByType(@Param("typeId") Integer typeId, @Param("stateId") Integer stateId);


    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,\n" +
            "commodity.round_time,template.cover_img_url,\n" +
            "commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id  " +
            "where template.name like #{name} and commodity.state_id = #{stateId}")

    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "name",property = "name"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "round_time",property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectByName(@Param("name") String name, @Param("stateId") Integer stateId);

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,\n" +
            "commodity.round_time,template.cover_img_url,\n" +
            "commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id  " +
            "where template.commodity_type_id = #{typeId} and template.name like #{name} and commodity.state_id = #{stateId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectByTypeAndName(@Param("name") String name, @Param("typeId") Integer typeId, @Param("stateId") Integer stateId);

    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,\n" +
            "commodity.round_time,template.cover_img_url,\n" +
            "commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id  " +
            "where commodity.buy_current_number/template.buy_total_number>1/2" +
            " and commodity.buy_current_number!=template.buy_total_number")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectByGuess();


    @Select("SELECT commodity.id,template.name,commodity.view_num," +
            "template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id where commodity.state_id = 3 and template.buy_total_number<#{number}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")

    })
    List<Commoditys> selectHeight(@Param("number") Integer number);


    @Select("SELECT commodity.id,template.name,commodity.view_num,template.commodity_desc,template.commodity_type_id,commodity.buy_current_number,\n" +
            "template.buy_total_number,template.ground_time,commodity.luck_code_id,\n" +
            "commodity.round_time,template.cover_img_url,template.auto_round,template.commodity_desc_url,\n" +
            "commodity.buy_last_number,commodity.sell_out_time,commodity.state_id\n" +
            "FROM t_commoditys as commodity LEFT JOIN t_commodity_template as template ON\n" +
            "commodity.temp_id = template.id where commodity.state_id = 1 ORDER by commodity.sell_out_time DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "commodity_desc", property = "commodityDesc"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "luck_code_id", property = "luckCodeId"),
            @Result(column = "round_time", property = "roundTime"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),
            @Result(column = "auto_round", property = "autoRound"),
            @Result(column = "commodity_desc_url", property = "commodityDescUrl"),
            @Result(column = "buy_last_number", property = "byLastNumber"),
            @Result(column = "sell_out_time", property = "sellOutTime"),
            @Result(column = "state_id", property = "stateId"),
            @Result(column = "view_num",property = "viewNum")
    })
    List<Commoditys> selectHasTheLotteryComm();
}