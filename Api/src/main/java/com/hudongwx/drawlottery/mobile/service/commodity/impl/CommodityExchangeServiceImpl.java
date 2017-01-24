package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;
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
    CommodityExchangeMapper ceMapper;
    @Autowired
    ExchangeWayMapper ewMapper;

    @Override
    public boolean addExchange(CommodityExchange exchange) {
        return ceMapper.insert(exchange) > 0;
    }

    @Override
    public boolean deleteExchange(CommodityExchange exchange) {
        return ceMapper.delete(exchange) > 0;
    }

    @Override
    public boolean updateExchange(CommodityExchange exchange) {
        return ceMapper.updateByPrimaryKeySelective(exchange) > 0;
    }

    @Override
    public List<Map<String, Object>> selectByCommodityId(Long commId) {
        //查询商品的兑换方式
        List<CommodityExchange> exchangeList = ceMapper.selectExcInfoByCommodityId(commId);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (CommodityExchange commExc : exchangeList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", commExc.getExchangeWayId());//兑换方式id
            map.put("exchangeWay", commExc.getEwName());//兑换方式名称
            map.put("url", Settings.SERVER_URL_PATH + commExc.getEwUrl());//接口url
            map.put("quota", 0);
            mapList.add(map);
        }
        return mapList;
    }


}
