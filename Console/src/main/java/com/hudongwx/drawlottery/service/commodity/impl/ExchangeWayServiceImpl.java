package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.dao.ExchangeWayMapper;
import com.hudongwx.drawlottery.dao.TempExchangeMapper;
import com.hudongwx.drawlottery.pojo.ExchangeWay;
import com.hudongwx.drawlottery.pojo.TempExchange;
import com.hudongwx.drawlottery.service.commodity.ExchangeWayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/18 0018
 * Time: 13:42
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class ExchangeWayServiceImpl implements ExchangeWayService {

    @Resource
    private TempExchangeMapper txMapper;
    @Resource
    private ExchangeWayMapper exchangeWayMapper;

    @Resource
    private CommonConstants commonConstants;
    /**
     * 添加模板、付款方式联系到表中
     *
     * @param tempId         模板id
     * @param exchangeWayIds 付款方式集合
     */
    @Override
    public void addCommodityExchange(long tempId, List<Integer> exchangeWayIds) {
        List<TempExchange> list = new ArrayList<>();
        for (Integer id : exchangeWayIds) {
            final TempExchange tempExchange = new TempExchange();
            tempExchange.setCommodityId(tempId);
            tempExchange.setExchangeWayId(id);
            tempExchange.setState(commonConstants.getVALID());
            list.add(tempExchange);
        }
        txMapper.insertList(list);
    }

    /**
     * 得到所有的兑换方式
     *
     * @return
     */
    @Override
    public List<ExchangeWay> getAll() {
        return exchangeWayMapper.selectAll();
    }


}
