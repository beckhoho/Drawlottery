package com.hudongwx.drawlottery.service.commodity;

import com.github.pagehelper.PageInfo;
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
     * 根据 id 得到商品模板.
     *
     * @param id 商品模板id
     * @return 商品模板
     */
    public CommodityTemplate getCommodityById(final long id);

    /**
     * 根据id集合获取模板
     *
     * @param ids id集合
     * @return
     */
    public List<CommodityTemplate> getCommodityById(final List<Integer> ids);

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

    /**
     * 获取模板列表
     *
     * @param currentPage 当前页数
     * @param maxPageSize 最大页数
     * @param type        分类
     * @param genre       类型
     * @param order       排序字段
     * @param direction   方向
     * @return 匹配分页
     */
    public PageInfo<CommodityTemplate> getTemplates(final int currentPage, int maxPageSize, List<Integer> type, List<Integer> genre, int order, int direction);

    /**
     * 上架模板（根据模板新建一个商品）
     *
     * @param list 模板id
     */
    public void groundNew(List<Integer> list);

    /**
     * 为模板关联图片列表
     *
     * @param id     模板id
     * @param images 图片url集合
     */
    public void connectImgs(long id, List<String> images);

    /**
     * 为模板关联付款方式
     *
     * @param id             模板id
     * @param exchangeWayIds 付款方式id集合
     */
    public void connectExchangeWay(long id, List<Integer> exchangeWayIds);
}
