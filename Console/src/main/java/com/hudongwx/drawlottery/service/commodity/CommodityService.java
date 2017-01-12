package com.hudongwx.drawlottery.service.commodity;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;

import java.util.Date;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 16:47
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface CommodityService {
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
     * 批量上架商品
     *
     * @param commodities 商品模板
     */
    public void groundCommodities(final List<CommodityTemplate> commodities);

    /**
     * 上架单个商品
     *
     * @param tempId        模板id
     * @param luckCodeCount 幸运码总量
     */
    public void groundCommodity(final long tempId, final long luckCodeCount);

    /**
     * 得到最大期数
     *
     * @return 期数
     */
    public long selectMaxRoundTime();

    /**
     * 添加商品
     *
     * @param commodity 商品
     * @return 状态
     */
    public int addCommodity(Commodity commodity);
}
