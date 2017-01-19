package com.hudongwx.drawlottery.service.commodity;

import com.hudongwx.drawlottery.pojo.ExchangeWay;

import java.util.List;

/**
 * 付款方式相关 service 类.
 * Date: 2017/1/18 0018
 * Time: 13:37
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface ExchangeWayService {
    /**
     * 添加模板、付款方式联系到表中
     *
     * @param tempId         模板id
     * @param exchangeWayIds 付款方式集合
     */
    public void addCommodityExchange(long tempId, List<Integer> exchangeWayIds);

    /**
     * 得到所有的兑换方式
     * @return
     */
    public List<ExchangeWay> getAll();
}
