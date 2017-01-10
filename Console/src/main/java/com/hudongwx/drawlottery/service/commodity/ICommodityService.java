package com.hudongwx.drawlottery.service.commodity;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;

import java.util.Date;
import java.util.List;

/**
 * 商品相关 service 接口.
 * Date: 2017/1/4 0004
 * Time: 16:57
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface ICommodityService {
    /**
     * 获取商品列表.
     *
     * @param currentPage            当前页数
     * @param pageSize               每页最大数量
     * @param genres                 商品属性（数据库对应商品类别）
     * @param types                  商品类型
     * @param key                    搜索关键字
     * @param statuses               商品状态
     * @param groundTimeFront        上架时间（开始）
     * @param groundTimeAfter        上架时间（结束）
     * @param undercarriageTimeFront 下架时间（开始）
     * @param undercarriageTimeAfter 下架时间（结束）
     * @param order                  排序字段
     * @param direction              排序方向
     * @param valid                  是否可用  TODO 用于回收站（预留）
     * @return 商品分页
     */
    public PageInfo<Commodity> getCommodities(final int currentPage,
                                              final int pageSize,
                                              final String key,
                                              final List<Integer> genres,
                                              final List<Integer> types,
                                              final List<Integer> statuses,
                                              final Date groundTimeFront,
                                              final Date groundTimeAfter,
                                              final Date undercarriageTimeFront,
                                              final Date undercarriageTimeAfter,
                                              final int order,
                                              final int direction,
                                              final int valid);

    /**
     * 根据商品 id 得到商品.
     *
     * @param id 商品id
     * @return 商品
     */
    public CommodityTemplate getCommodityById(final int id);

    /**
     * 添加商品.
     *
     * @param commodityTemplate 商品实体类
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
     * @param name 商品名
     * @return 匹配集合
     */
    public List<String> getNames(String name);
}
