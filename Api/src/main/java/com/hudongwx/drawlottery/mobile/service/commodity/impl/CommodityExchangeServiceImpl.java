package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;
import com.hudongwx.drawlottery.mobile.entitys.ExchangeWay;
import com.hudongwx.drawlottery.mobile.mappers.CommodityExchangeMapper;
import com.hudongwx.drawlottery.mobile.mappers.ExchangeWayMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityExchangeService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/9 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/9 20:36　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityExchangeServiceImpl implements ICommodityExchangeService {

    @Autowired
    CommodityExchangeMapper mapper;
    @Autowired
    ExchangeWayMapper ewMapper;

    @Override
    public boolean addExchange(CommodityExchange exchange) {
        return mapper.insert(exchange) > 0;
    }

    @Override
    public boolean deleteExchange(CommodityExchange exchange) {
        return mapper.delete(exchange) > 0;
    }

    @Override
    public boolean updateExchange(CommodityExchange exchange) {
        return mapper.updateByPrimaryKeySelective(exchange) > 0;
    }

    @Override
    public List<Map<String, Object>> selectByCommodityId(Long commId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<CommodityExchange> exchangeList = mapper.selectByCommodityId(commId);
        for (CommodityExchange ce : exchangeList) {
            Map<String, Object> map = new HashMap<>();
            ExchangeWay exchangeWay = ewMapper.selectByPrimaryKey(ce.getExchangeWayId());
            map.put("id", ce.getExchangeWayId());
            map.put("exchangeWay", exchangeWay.getName());
            map.put("url", Settings.SERVER_URL_PATH + exchangeWay.getUrl());
            map.put("quota", 0);
            mapList.add(map);
        }
        return mapList;
    }
}
