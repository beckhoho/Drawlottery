package com.hudongwx.drawlottery.service.history.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.dto.paramBody.HistoryParam;
import com.hudongwx.drawlottery.dao.HistoryMapper;
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
@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;


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
        PageHelper.startPage(currentPage,maxPageSize);
        return historyMapper.selectRoundTimes(key);
    }
}
