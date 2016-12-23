package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommoditysMapper extends BaseMapper<Commoditys> {

    //通过商品类型，查询该类型所有的商品
    @Select("select * from t_commoditys where commodity_type_id=#{commodTypeId}")
    List<Commoditys> selectTypeAll(@Param("commodTypeId") Integer commodTypeId);

    //查询指定的区域数据
    @Select("select * from t_commoditys where commodity_type_id = #{commodTypeId}  limit #{startNum},#{endNum}")
    List<Commoditys> selectPaging(@Param("commodTypeId") Integer commodTypeId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);

    //通过商品类型，查询该类型商品的总数；
    @Select("select count(*) from t_commoditys where commodity_type_id = #{commodTypeId} ")
    int selectTypeCount(@Param("commodTypeId") Integer commodTypeId);

    @Select("select * from t_commoditys where id = #{id}")
    Commoditys selectByid(@Param("id") Long id);

    @Insert("insert into t_commoditys(name,desc,commodity_type_id," +
            "genre,buy_current_number,buy_total_number,start_time,luck_code_id,state,round_time," +
            "cover_img_id,auto_round) values" +
            "(#{commod.getName()},#{commod.getDesc()},#{commod.getCommodityTypeId()}," +
            "#{commod.getGenre()},#{commod.getBuyCurrentNumber()},#{commod.getBuyTotalNumber()}," +
            "#{commod.getStartTime()},#{commod.getLuckCodeId()},#{commod.getState()}," +
            "#{commod.getRoundTime()},#{commod.getCoverImgId},#{commod.getAutoRound()})")
    boolean add(@Param("commod") Commoditys commod);

}