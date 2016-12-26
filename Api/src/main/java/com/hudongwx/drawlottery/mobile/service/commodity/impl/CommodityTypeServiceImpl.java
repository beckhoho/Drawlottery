package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.mappers.CommodityTypeMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CommodityTypeMapper commodType;

    /**
     *添加商品对象
     * @param commtype  商品类型对象
     * @return
     */
    @Override
    public boolean addType(CommodityType commtype) {
        int insert = commodType.insert(commtype);
        if(insert>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @param id   id
     * @return
     */
    @Override
    public boolean hideType(Integer id) {
        CommodityType c = new CommodityType();
        c.setId(1);
        c.setState(0);
        int i = commodType.updateByPrimaryKeySelective(c);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     *  通過主鍵修改對象，不修改空值
     * @param commtype  商品類型對象
     * @return
     */
    @Override
    public boolean updateType(CommodityType commtype) {
        int i = commodType.updateByPrimaryKeySelective(commtype);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     * 通過類型名去查詢當前商品類型
     * @param name  商品類型名
     * @return
     */
    @Override
    public CommodityType selectType(String name) {
        CommodityType ct = new CommodityType();
        ct.setName(name);
        return (CommodityType) commodType.select(ct);
    }
}
