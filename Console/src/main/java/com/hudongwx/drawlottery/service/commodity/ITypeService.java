package com.hudongwx.drawlottery.service.commodity;

import com.hudongwx.drawlottery.pojo.CommodityType;

import java.util.List;

/**
 * 商品类型相关 service 接口.
 * Date: 2017/1/6 0006
 * Time: 16:16
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface ITypeService {
    /**
     * 通过 id 获取类型.
     *
     * @param id 类型id
     * @return 类型实体
     */
    public CommodityType getTypeById(final int id);

    /**
     * 通过 name 名字获取类型.
     *
     * @param name 类型名
     * @return 类型实体
     */
    public CommodityType getTypeByName(final String name);

    /**
     * 获取所有类型
     * @return 返回类型列表
     */
    public List<CommodityType> getAllTypes();

    public void addType(CommodityType type);

    public void updateType(CommodityType type);

    public void deleteTypes(List<Integer> ids);
}
