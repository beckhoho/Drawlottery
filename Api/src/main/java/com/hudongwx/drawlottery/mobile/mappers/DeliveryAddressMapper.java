package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import org.apache.ibatis.annotations.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public interface DeliveryAddressMapper extends BaseMapper<DeliveryAddress>{

    @Insert("insert into user_delivery_address (userId,consignee,consigneePhone,deliveryAddress,default)values(#{userId},#{consignee},#{consigneePhone},#{deliveryAddress},#{default})")
    int addDeliveryAddress(@Param("dlvr") DeliveryAddress da);

    @Delete("delete from user_delivery_address where userId = #{userId}")
    int deleteByUserId(@Param("userId") String userId);

    @Update("update user_delivery_address set consignee = #{consignee},consigneePhone = #{consigneePhone},deliveryAddress=#{deliveryAddress},default=#{default} where userId = #{userId}")
    int updateDeliveryAddress(@Param("dlvr") DeliveryAddress da);

    @Select("select * from user_delivery_address where userId = #{userId}")
    DeliveryAddress selectByUserId(@Param("userId") String userId);
}
