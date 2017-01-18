package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.dao.CommodityTypeMapper;
import com.hudongwx.drawlottery.pojo.CommodityType;
import com.hudongwx.drawlottery.service.commodity.ITypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private CommonConstants commonConstants;
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

    /**
     * 获取所有类型
     *
     * @return 返回类型列表
     */
    @Override
    public List<CommodityType> getAllTypes() {
        return commodityTypeMapper.selectAll();
    }

    @Override
    public void addType(CommodityType type) {
        type.setState(commonConstants.getVALID());
        commodityTypeMapper.insertSelective(type);
    }

    @Override
    public void updateType(CommodityType type) {
        commodityTypeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public void deleteTypes(List<Integer> ids) {
        for (Integer id : ids) {
            commodityTypeMapper.deleteByPrimaryKey(id);
        }
    }
}
