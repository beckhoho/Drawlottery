package com.hudongwx.drawlottery.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.dao.CommodityMapper;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.service.commodity.ICommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/6 0006
 * Time: 10:42
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class ICommodityServiceImpl implements ICommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    /**
     * 获取商品列表.
     *
     * @param currentPage            当前页数
     * @param pageSize               每页最大数量
     * @param key                    搜索关键字
     * @param genre                 商品属性（数据库对应商品类别）
     * @param type                  商品类型
     * @param state               商品状态
     * @param groundTimeFront        上架时间（开始）
     * @param groundTimeAfter        上架时间（结束）
     * @param undercarriageTimeFront 下架时间（开始）
     * @param undercarriageTimeAfter 下架时间（结束）
     * @param order                  排序字段
     * @param direction              排序方向（0：降序，1：升序）
     * @param valid                  是否可用  TODO 用于回收站（预留）
     * @return 商品分页
     */
    @Override
    public PageInfo<Commodity> getCommodities(int currentPage, int pageSize, String key, List<Integer> genre,
                                              List<Integer> type, List<Integer> state, Date groundTimeFront,
                                              Date groundTimeAfter, Date undercarriageTimeFront,
                                              Date undercarriageTimeAfter, final int order, int direction, int valid) {
        PageHelper.startPage(currentPage, pageSize);
        final List<Commodity> commodities = commodityMapper.selectCommodities(key, genre, type, state, groundTimeFront,
                groundTimeAfter, undercarriageTimeFront, undercarriageTimeAfter, order, direction, valid);
        return new PageInfo<>(commodities);
    }

    /**
     * 根据商品 id 得到商品.
     *
     * @param id 商品id
     * @return 商品
     */
    @Override
    public Commodity getCommodityById(int id) {
        return null;
    }

    /**
     * 添加商品.
     *
     * @param commodity 商品实体类
     * @return 状态
     */
    @Override
    public int addCommodity(Commodity commodity) {
        return commodityMapper.insertSelective(commodity);
    }

    /**
     * 修改商品信息.
     *
     * @param commodity 修改实体类
     * @return 状态
     */
    @Override
    public int updateCommodity(Commodity commodity) {
        return 0;
    }

    /**
     * 批量删除商品.
     *
     * @param commodityId 删除商品的id集合
     * @return 状态
     */
    @Override
    public int deleteCommodity(List<Integer> commodityId) {
        return commodityMapper.batchDelete(commodityId);
    }

    /**
     * 批量上架商品.
     *
     * @param commodityIds 商品id集合
     * @return 状态
     */
    @Override
    public int ground(List<Integer> commodityIds) {
        return commodityMapper.updateState(commodityIds, Commodity.ON_SALE, new Date(), null);
    }

    /**
     * 批量下架商品.
     *
     * @param commodityIds 商品id集合
     * @return 状态
     */
    @Override
    public int undercarriage(List<Integer> commodityIds) {
        return commodityMapper.updateState(commodityIds, Commodity.DID_SALE, null, new Date());
    }
}
