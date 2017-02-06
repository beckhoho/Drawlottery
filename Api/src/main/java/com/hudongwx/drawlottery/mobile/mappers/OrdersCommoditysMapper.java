package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.OrdersCommoditys;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 13:47　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface OrdersCommoditysMapper extends BaseMapper<OrdersCommoditys>{

    List<Long> selectCommIdByOrderId(@Param("orderId")Long orderId);

    int countUserCommAmount(@Param("orderIdList")List<Long> orderIdList,@Param("commId")Long commId);

    List<CommodityAmount> selectAmountOrders(Long orderId);
}
