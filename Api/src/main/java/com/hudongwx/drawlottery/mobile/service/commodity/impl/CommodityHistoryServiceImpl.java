package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.mappers.CommodityHistoryMapper;
import com.hudongwx.drawlottery.mobile.mappers.ExchangeWayMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.mappers.VirtualCommodityMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/10 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/10 11:46　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityHistoryServiceImpl implements ICommodityHistoryService {

    @Autowired
    CommodityHistoryMapper mapper;
    @Autowired
    ExchangeWayMapper exMapper;
    @Autowired
    VirtualCommodityMapper virtMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addCommodHistory(CommodityHistory commodityHistory) {
        return false;
    }

    @Override
    public CommodityHistory selectHistoryCommod() {
        /*CommodityHistory history = new CommodityHistory();
        history.setLuckUserAccountId(10);
        mapper.selectByPrimaryKey(history);*/
        return null;
    }

}
