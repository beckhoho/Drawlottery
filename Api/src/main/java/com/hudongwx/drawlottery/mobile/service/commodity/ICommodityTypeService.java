package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;

import java.util.List;

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
    boolean hideType(Integer id);

    //修改类型
    boolean updateType(CommodityType commtype);

    //通过类型名查询商品类型信息
    CommodityType selectType(String name);

    /**
     * 获取可用商品类型
     *
     * @return
     */
    List<CommodityType> selectAvailable();

    /**
     * 获取所有商品类型
     *
     * @return
     */
    List<CommodityType> selectAll();
}
