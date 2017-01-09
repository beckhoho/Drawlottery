package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;
import com.hudongwx.drawlottery.mobile.mappers.CommodityExchangeMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 创建　kiter　2017/1/9 20:36　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityExchangeServiceImpl implements ICommodityExchangeService{

    @Autowired
    CommodityExchangeMapper mapper;
    @Override
    public boolean addExchange(CommodityExchange exchange) {
        return mapper.insert(exchange)>0;
    }

    @Override
    public boolean deleteExchange(CommodityExchange exchange) {
        return mapper.delete(exchange)>0;
    }

    @Override
    public boolean updateExchange(CommodityExchange exchange) {
        return mapper.updateByPrimaryKeySelective(exchange)>0;
    }

    @Override
    public List<CommodityExchange> selectByCommodityId(Long commodityId) {
        return mapper.selectByCommodityId(commodityId);
    }
}
