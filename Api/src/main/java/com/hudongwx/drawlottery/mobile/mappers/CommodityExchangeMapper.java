package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/9 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/9 20:29　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface CommodityExchangeMapper extends BaseMapper<CommodityExchange> {

    @Select("select * from t_commodity_exchange where commodity_id = #{commodityId}")
    @Results({
            @Result(column = "commodity_id", property = "commodityId"),
            @Result(column = "exchange_way_id", property = "exchangeWayId")
    })
    List<CommodityExchange> selectByCommodityId(@Param("commodityId") Long commodityId);

}
