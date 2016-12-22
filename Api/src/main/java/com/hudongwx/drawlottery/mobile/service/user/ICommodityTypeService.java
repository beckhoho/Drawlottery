package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 17:34　<br/>
 * <p>
 * ***这是什么类
 * <p>
 * @email 294786949@qq.com
 */
public interface ICommodityTypeService {
    //添加类型
    boolean addType(CommodityType commtype);

    //隐藏类型
    boolean hideType(String typeName);

    //修改类型
    boolean updateType(CommodityType commtype);
}
