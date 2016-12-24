package com.hudongwx.drawlottery.mobile.service.oder;

import com.hudongwx.drawlottery.mobile.entitys.Orders;

import java.util.List;

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
public interface IOdersService {

    //创建订单
    boolean addOder(Orders oders);

    //查看订单详情
    public List<Orders> selectByUserAccount(Long userAccount);

    //删除订单
    boolean deleteOder(Long id);

    //修改订单
    boolean update(Orders  oders);


}
