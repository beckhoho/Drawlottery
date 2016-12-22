package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:56　<br/>
 * <p>
 *          商品种类service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityTypeServiceImpl implements ICommodityTypeService{

    @Override
    public boolean addType(CommodityType commtype) {
        return false;
    }

    @Override
    public boolean hideType(String typeName) {
        return false;
    }

    @Override
    public boolean updateType(CommodityType commtype) {
        return false;
    }
}
