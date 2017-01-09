package com.hudongwx.drawlottery.mobile.service.order;

import com.hudongwx.drawlottery.mobile.entitys.OrdersCommoditys;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 13:53　<br/>
 * <p>
 *          订单商品对应表service
 * <p>
 * @email 346905702@qq.com
 */
public interface IOrdersCommoditysService {

    //添加订单商品
    boolean addOrdersCommodity(OrdersCommoditys commoditys);

    //删除订单商品
    boolean deleteOrdersCommodity(OrdersCommoditys commoditys);

    //修改订单商品
    boolean updateOrdersCommodity(OrdersCommoditys commoditys);

    //查看订单商品
    List<OrdersCommoditys> selectCommoditys(Long ordersId);

}
