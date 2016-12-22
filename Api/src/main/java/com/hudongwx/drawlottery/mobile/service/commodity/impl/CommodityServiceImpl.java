package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 17:38　<br/>
 * <p>
 * ***这是什么类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class CommodityServiceImpl implements ICommodityService {

    @Override
    public boolean addCommodity(Commoditys commod) {
        return false;
    }

    @Override
    public boolean delete(Commoditys commod) {
        return false;
    }

    @Override
    public Commoditys select(String name, Long roundTime) {
        return null;
    }

    @Override
    public boolean update(Commoditys commod, Integer id) {
        return false;
    }
}
