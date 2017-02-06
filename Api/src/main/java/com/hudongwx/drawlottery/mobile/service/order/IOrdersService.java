package com.hudongwx.drawlottery.mobile.service.order;

import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 19:35　<br/>
 * <p>
 *      订单类service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface IOrdersService {

    //创建订单
    Long pay(Long accountId, Orders orders, List<CommodityAmount> commodityAmounts);

    //查看订单详情
    List<Orders> selectByUserAccount(Long userAccount);

    //删除订单
    boolean deleteOder(Long id);

    //修改订单
    boolean update(Orders oders);

    //订单界面需要的数据
    Map<String,Object> selectUsableRedPackets(Long accountId, Integer sum);

    //支付成功界面数据
    Map<String,Object> selectPaySuccess(Long accountId,Long orderId);
}
