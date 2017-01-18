package com.hudongwx.drawlottery.service.order.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.dao.OrderMapper;
import com.hudongwx.drawlottery.pojo.Order;
import com.hudongwx.drawlottery.service.order.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单相关 service 实现类.
 * Date: 2017/1/18 0018
 * Time: 10:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
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
    @Override
    public PageInfo<Order> getOrders(int currentPage, int pageSize, List<Integer> payModeId, Date submitDateFront, Date submitDateAfter, int order, int direction) {
        PageHelper.startPage(currentPage,pageSize);
        List<Order> list = orderMapper.selectList(payModeId,submitDateFront,submitDateAfter,order,direction);
        return null;
    }
}
