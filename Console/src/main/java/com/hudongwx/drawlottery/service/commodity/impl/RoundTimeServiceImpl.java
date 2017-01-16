package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.service.commodity.CommodityService;
import com.hudongwx.drawlottery.service.commodity.RoundTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 17:18
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class RoundTimeServiceImpl implements RoundTimeService {
    @Resource
    private CommodityService commodityService;

    @Override
    public Long generateNewRoundTime() {
        return getMaxRoundTime() + 1;
    }

    /**
     * 得到最大期数
     *
     * @return 总量
     */
    @Override
    public long getMaxRoundTime() {
        return commodityService.selectMaxRoundTime();
    }
}
