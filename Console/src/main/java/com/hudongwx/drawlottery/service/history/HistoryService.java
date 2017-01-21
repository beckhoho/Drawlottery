package com.hudongwx.drawlottery.service.history;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.dto.paramBody.HistoryParam;
import com.hudongwx.drawlottery.pojo.Express;
import com.hudongwx.drawlottery.pojo.History;

import java.util.List;

/**
 * 订单相关 service 类.
 * Date: 2017/1/18 0018
 * Time: 2:50
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface HistoryService {

    /**
     * 得到历史集合.
     *
     * @param currentPage 当前页数
     * @param maxPageSize 每页最大数量
     * @param param       条件{@link HistoryParam}
     * @return 分页
     */
    public PageInfo<History> getHistoryList(final int currentPage, final int maxPageSize, HistoryParam param);

    /**
     * 得到期数集合
     *
     * @param currentPage 当前页数
     * @param maxPageSize 每页最大数量
     * @param key         期数关键字
     * @return 列表
     */
    public List<String> getRoundTimes(int currentPage, int maxPageSize, String key);

    /**
     * 通过id得到详情
     *
     * @param id 商品id
     * @return 详情
     */
    History getHistory(long id);

    /**
     * 发货
     * @param express 发货信息
     */
    void delivery(Express express);

    /**
     * 删除记录
     * @param id 商品id
     */
    void delete(Long id);
}
