package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliveryAddressMapper extends BaseMapper<DeliveryAddress> {

    /**
     *  通过用户Id查询收货地址
     *
     * @param accountId
     * @return
     */
    List<DeliveryAddress> selectByUserAccountId(@Param("accountId") Long accountId);
}