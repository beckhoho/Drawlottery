package com.hudongwx.drawlottery.service.history.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.paramBody.HistoryParam;
import com.hudongwx.drawlottery.common.exceptions.ServiceException;
import com.hudongwx.drawlottery.dao.ExpressMapper;
import com.hudongwx.drawlottery.dao.HistoryMapper;
import com.hudongwx.drawlottery.pojo.Express;
import com.hudongwx.drawlottery.pojo.History;
import com.hudongwx.drawlottery.service.history.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单相关 service 实现类.
 * Date: 2017/1/18 0018
 * Time: 10:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private ExpressMapper expressMapper;
    @Resource
    private LangConstants langConstants;

    /**
     * 得到历史集合.
     *
     * @param currentPage 当前页数
     * @param maxPageSize 每页最大数量
     * @param param       条件{@link HistoryParam}
     * @return 分页
     */
    @Override
    public PageInfo<History> getHistoryList(int currentPage, int maxPageSize, HistoryParam param) {
        PageHelper.startPage(currentPage, maxPageSize);
        List<History> list = historyMapper.selectList(param);
        return new PageInfo<>(list);
    }

    /**
     * 得到期数集合
     *
     * @param currentPage 当前页数
     * @param maxPageSize 每页最大数量
     * @param key         期数关键字
     * @return 列表
     */
    @Override
    public List<String> getRoundTimes(int currentPage, int maxPageSize, String key) {
        PageHelper.startPage(currentPage, maxPageSize);
        return historyMapper.selectRoundTimes(key);
    }

    /**
     * 通过id得到详情
     *
     * @param id 商品id
     * @return 详情
     */
    @Override
    public History getHistory(long id) {
        return historyMapper.selectHistory(id);
    }

    /**
     * 发货
     *
     * @param express 发货信息
     */
    @Override
    public void delivery(Express express) {
        if (StringUtil.isEmpty(express.getDeliveryName()))
            throw new ServiceException(langConstants.getLang(langConstants.DELIVERY_NAME_CAN_NOT_NULL));
        if (StringUtil.isEmpty(express.getDeliveryNumber()))
            throw new ServiceException(langConstants.getLang(langConstants.DELIVERY_NUMBER_CAN_NOT_NULL));
        express.setState(1);
        expressMapper.updateByPrimaryKeySelective(express);
    }

    /**
     * 删除记录
     *
     * @param id 商品id
     */
    @Override
    public void delete(Long id) {
        historyMapper.deleteByPrimaryKey(id);
    }
}
