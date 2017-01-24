package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;
import org.apache.ibatis.annotations.Param;

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

    //通过商品ID查询商品兑换方式
    List<CommodityExchange> selectExcInfoByCommodityId(@Param("commodityId") Long commodityId);

    List<CommodityExchange> selectByIdList(@Param("idList") List<Long> idList);
}
