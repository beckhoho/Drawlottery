package com.hudongwx.drawlottery.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.exceptions.ServiceException;
import com.hudongwx.drawlottery.dao.CommodityMapper;
import com.hudongwx.drawlottery.dao.CommodityTemplateMapper;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;
import com.hudongwx.drawlottery.service.commodity.ICommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品相关 service 实现类.
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

    @Resource
    private CommodityTemplateMapper tempMapper;

    @Resource
    private LangConstants langConstants;

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
     * 根据商品 id 得到商品.
     *
     * @param id 商品id
     * @return 商品
     */
    @Override
    public CommodityTemplate getCommodityById(int id) {
        return null;
    }

    /**
     * 添加商品.
     *
     * @param commodityTemplate 商品实体类
     * @return 状态
     */
    @Override
    public int addCommodityTemplate(CommodityTemplate commodityTemplate) {

        //字段校验
        if (null == commodityTemplate.getName())
            throw new ServiceException(langConstants.getLang(langConstants.TITLE_IS_NOT_NULL));
        if (null == commodityTemplate.getGenre())
            throw new ServiceException(langConstants.getLang(langConstants.GENRE_NOT_NULL));
        if (null == commodityTemplate.getCommodityTypeId())
            throw new ServiceException(langConstants.getLang(langConstants.TYPE_NOT_NULL));

        //字段补充
        if (null == commodityTemplate.getGroundTime())
            commodityTemplate.setGroundTime(new Date());
        if (new Date().compareTo(commodityTemplate.getGroundTime()) <= 0)
            commodityTemplate.setStateId(CommodityTemplate.WILL_SALE);
        else
            commodityTemplate.setStateId(CommodityTemplate.ON_SALE);
        commodityTemplate.setValid(1);
        //commodityTemplate.setCoverImgUrl("http://pic93.nipic.com/file/20160318/20584984_105122996275_2.jpg");
        return tempMapper.insert(commodityTemplate);
    }

    /**
     * 修改商品信息.
     *
     * @param commodityTemplate 修改实体类
     * @return 状态
     */
    @Override
    public int updateCommodity(CommodityTemplate commodityTemplate) {
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
        return commodityMapper.updateState(commodityIds, CommodityTemplate.ON_SALE, new Date(), null);
    }

    /**
     * 批量下架商品.
     *
     * @param commodityIds 商品id集合
     * @return 状态
     */
    @Override
    public int undercarriage(List<Integer> commodityIds) {
        return commodityMapper.updateState(commodityIds, CommodityTemplate.DID_SALE, null, new Date());
    }

    /**
     * 通过模糊搜索得到字段结果（限制了结果条数）
     *
     * @param name 商品名
     * @return 匹配集合
     */
    @Override
    public List<String> getNames(String name) {
        return commodityMapper.selectNames(name);
    }

}
