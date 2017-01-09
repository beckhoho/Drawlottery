package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.ExchangeWay;
import com.hudongwx.drawlottery.mobile.mappers.ExchangeWayMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeWayService;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
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
 * 创建　kiter　2017/1/9 20:55　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class ExchangeWayServiceImpl implements IExchangeWayService{

    @Autowired
    ExchangeWayMapper mapper;
    @Override
    public boolean addExchangeWay(ExchangeWay way) {
        return mapper.insert(way)>0;
    }

    @Override
    public boolean deleteExchangeWay(ExchangeWay way) {
        return mapper.delete(way)>0;
    }

    @Override
    public boolean updateExchangeWay(ExchangeWay way) {
        return mapper.updateByPrimaryKeySelective(way)>0;
    }

    @Override
    public List<ExchangeWay> selectAll() {
        return mapper.selectAll();
    }
}
