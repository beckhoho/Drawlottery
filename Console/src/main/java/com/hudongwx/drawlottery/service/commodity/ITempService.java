package com.hudongwx.drawlottery.service.commodity;

import com.hudongwx.drawlottery.pojo.CommodityTemplate;

import java.util.List;

/**
 * 商品模板相关 service 接口.
 * Date: 2017/1/4 0004
 * Time: 16:57
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface ITempService {

    /**
     * 根据商品 id 得到商品模板.
     *
     * @param id 商品模板id
     * @return 商品模板
     */
    public CommodityTemplate getCommodityById(final long id);

    /**
     * 添加商品模板.
     *
     * @param commodityTemplate 商品模板实体类
     * @return 状态
     */
    public int addCommodityTemplate(final CommodityTemplate commodityTemplate);

    /**
     * 修改商品信息.
     *
     * @param commodityTemplate 修改实体类
     * @return 状态
     */
    public int updateCommodity(final CommodityTemplate commodityTemplate);

    /**
     * 批量删除商品.
     *
     * @param commodityId 删除商品的id集合
     * @return 状态
     */
    public int deleteCommodity(final List<Integer> commodityId);

    /**
     * 批量上架商品.
     *
     * @param commodityIds 商品id集合
     * @return 状态
     */
    public int ground(final List<Integer> commodityIds);

    /**
     * 批量下架商品.
     *
     * @param commodityIds 商品id集合
     * @return 状态
     */
    public int undercarriage(final List<Integer> commodityIds);

    /**
     * 通过模糊搜索得到字段结果（限制了结果条数）
     *
     * @param name 商品名
     * @return 匹配集合
     */
    public List<String> getNames(String name);
}
