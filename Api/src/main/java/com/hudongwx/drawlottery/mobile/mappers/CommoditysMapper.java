package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommoditysMapper extends BaseMapper<Commoditys> {

    //查询指定的区域数据
    @Select("select * from t_commoditys where commodity_type_id = #{commodTypeId}  limit #{startNum},#{endNum}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")

    })
    List<Commoditys> selectPaging(@Param("commodTypeId") Integer commodTypeId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);


    @Select("select count(id) from t_commoditys where commodity_type_id = #{commodTypeId} ")
    int selectTypeCount(@Param("commodTypeId") Integer commodTypeId);


    @Select("select id from t_commodity_type where name = #{commodType} ")
    Long selectType(@Param("commodType") String commodType);

    @Select("SELECT * FROM t_commoditys ORDER BY buy_current_number/buy_total_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")
    })
    List<Commoditys> selectByTemp1();

    @Select("SELECT * FROM t_commoditys ORDER BY buy_current_number-buy_last_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")
    })
    List<Commoditys> selectByTemp2();

    @Select("SELECT * FROM t_commoditys ORDER BY start_time DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")
    })
    List<Commoditys> selectByTemp3();

    @Select("SELECT * FROM t_commoditys ORDER BY buy_total_number DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")
    })
    List<Commoditys> selectByTemp4();

    @Select("SELECT * FROM t_commoditys WHERE buy_total_number-buy_current_number=0 AND state=2")
    @Results({
            @Result(id = true, property = "id", column = "id"),
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
            @Result(column = "sell_out_time", property = "sellOutTime")
    })
    List<Commoditys> selectOnLottery();


    @Select("select id,commodity_type_id,buy_current_number,buy_total_number,cover_img_url,state,name from t_commoditys where commodity_type_id  =  #{typeId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),

    })
    List<Commoditys> selectByType(@Param("typeId") Integer typeId);


    @Select("select id,commodity_type_id,buy_current_number,buy_total_number,cover_img_url,state,name from t_commoditys where name like #{name}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),

    })
    List<Commoditys> selectByName(@Param("name") String name);

    @Select("select id,commodity_type_id,buy_current_number,buy_total_number,cover_img_url,state,name from t_commoditys where commodity_type_id = #{typeId} and name like #{name}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "commodity_type_id", property = "commodityTypeId"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),

    })
    List<Commoditys> selectByTypeAndName(@Param("name") String name, @Param("typeId") Integer typeId);

    @Select("select id,buy_current_number,buy_total_number,cover_img_url,name,state from t_commoditys where buy_current_number/buy_total_number>1/2 and buy_current_number!=buy_total_number")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),

    })
    List<Commoditys> selectByGuess();

    @Select("select id,buy_current_number,buy_total_number,cover_img_url,name,state from t_commoditys where buy_total_number<#{number}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "buy_current_number", property = "buyCurrentNumber"),
            @Result(column = "buy_total_number", property = "buyTotalNumber"),
            @Result(column = "cover_img_url", property = "coverImgUrl"),

    })
    List<Commoditys> selectHeight(@Param("number") Integer number);
}