package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.dao.CommodityTypeMapper;
import com.hudongwx.drawlottery.pojo.CommodityType;
import com.hudongwx.drawlottery.service.commodity.ITypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/6 0006
 * Time: 16:20
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("typeService")
public class TypeServiceImpl implements ITypeService {
    @Resource
    private CommodityTypeMapper commodityTypeMapper;

    /**
     * 通过 id 获取类型.
     *
     * @param id 类型id
     * @return 类型实体
     */
    @Override
    public CommodityType getTypeById(int id) {
        return commodityTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过 name 名字获取类型.
     *
     * @param name 类型名
     * @return 类型实体
     */
    @Override
    public CommodityType getTypeByName(String name) {
        return commodityTypeMapper.selectByName(name);
    }
}
