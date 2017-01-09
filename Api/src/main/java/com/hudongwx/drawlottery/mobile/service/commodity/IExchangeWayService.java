package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.ExchangeWay;

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
 * 创建　kiter　2017/1/9 14:07　<br/>
 * <p>
 *              兑奖方式service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface IExchangeWayService {

    //添加兑换方式
    boolean addExchangeWay(ExchangeWay way);

    //删除兑换方式
    boolean deleteExchangeWay(ExchangeWay way);

    //修改兑换方式
    boolean updateExchangeWay(ExchangeWay way);

    //查看兑换方式
    List<ExchangeWay> selectAll();
}