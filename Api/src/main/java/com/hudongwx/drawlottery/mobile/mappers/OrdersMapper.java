package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersMapper extends BaseMapper<Orders> {

    //查询用户订单
    List<Orders> selectByUserAccount(@Param("userAccount") Long userAccount);

    List<Orders> selectByUserDate(@Param("userAccount") Long userAccount,@Param("dateTime")Long date);
}