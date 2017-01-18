package com.hudongwx.drawlottery.service.order;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.pojo.Order;

import java.util.Date;
import java.util.List;

/**
 * 订单相关 service 类.
 * Date: 2017/1/18 0018
 * Time: 2:50
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface OrderService {
    /**
     * * 得到订单分页.
     *
     * @param currentPage     当前页数
     * @param pageSize        分页大小
     * @param payModeId      支付方式id集合
     * @param submitDateFront 时间筛选
     * @param submitDateAfter 时间筛选
     * @param order           排序
     * @param direction       方向
     * @return 分页
     */
    public PageInfo<Order> getOrders(final int currentPage,
                                     final int pageSize,
                                     final List<Integer> payModeId,
                                     final Date submitDateFront,
                                     final Date submitDateAfter,
                                     final int order,
                                     final int direction);


}
