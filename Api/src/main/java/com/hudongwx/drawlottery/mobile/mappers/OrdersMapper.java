package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("select * from t_orders where user_account_id = #{userAccount}")
    List<Orders> selectByUserAccount(@Param("userAccount") Long userAccount);
}