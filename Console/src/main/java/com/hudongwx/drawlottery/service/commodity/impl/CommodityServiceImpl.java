package com.hudongwx.drawlottery.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.dao.CommodityMapper;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;
import com.hudongwx.drawlottery.service.commodity.CommodityService;
import com.hudongwx.drawlottery.service.commodity.LuckCodeService;
import com.hudongwx.drawlottery.service.commodity.RoundTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service
public class CommodityServiceImpl implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private RoundTimeService roundTimeService;
    @Resource
    private LuckCodeService luckCodeService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取商品列表.
     *
     * @param currentPage            当前页数
     * @param pageSize               每页最大数量
     * @param key                    搜索关键字
     * @param genre                  商品属性（数据库对应商品类别）
     * @param type                   商品类型
     * @param state                  商品状态
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
    public PageInfo<Commodity> getCommodities(final int currentPage,
                                              final int pageSize,
                                              final String key,
                                              final List<Integer> genre,
                                              final List<Integer> type,
                                              final List<Integer> state,
                                              final Date groundTimeFront,
                                              final Date groundTimeAfter,
                                              final Date undercarriageTimeFront,
                                              final Date undercarriageTimeAfter,
                                              final int order,
                                              final int direction,
                                              final int valid) {
        PageHelper.startPage(currentPage, pageSize);

        final List<Commodity> commodities =
                commodityMapper.selectCommodities(key, genre, type, state, groundTimeFront,
                        groundTimeAfter, undercarriageTimeFront, undercarriageTimeAfter, order, direction, valid);

        return new PageInfo<>(commodities);
    }

    /**
     * 批量上架商品
     *
     * @param commodities 商品模板
     * @deprecated 删除，写法有误
     */
    @Override
    public void groundCommodities(final List<CommodityTemplate> commodities) {
        for (CommodityTemplate commodity : commodities) {
            groundCommodity(commodity.getId(), commodity.getBuyTotalNumber());
        }
    }

    /**
     * 上架单个商品
     *
     * @param tempId        模板id
     * @param luckCodeCount 幸运码总量
     */
    @Override
    public void groundCommodity(final long tempId, final long luckCodeCount) {
        //线程安全
        synchronized (this) {
            logger.info("生成开始");
            final long start = System.currentTimeMillis();
            //检索生成幸运码
            luckCodeService.generate(luckCodeCount);
            final Commodity commodity = new Commodity();
            commodity.setBuyCurrentNumber(0);
            commodity.setRoundTime("" + roundTimeService.generateNewRoundTime());
            commodity.setViewNum(0L);
            commodity.setTempId(tempId);
            commodity.setStateId(Commodity.ON_SALE);
            addCommodity(commodity);
            //关联幸运码
            luckCodeService.connect(commodity.getCommodityId(), luckCodeCount);
            logger.info("生成结束："+(System.currentTimeMillis()-start));
        }
    }

    /**
     * 得到最大期数
     *
     * @return 期数
     */
    @Override
    public long selectMaxRoundTime() {
        return commodityMapper.selectMaxRoundTime();
    }

    /**
     * 添加商品
     *
     * @param commodity 商品
     * @return 状态
     */
    @Override
    public int addCommodity(Commodity commodity) {
        return commodityMapper.insertCommodity(commodity);
    }
}
