package com.hudongwx.drawlottery.service.commodity;

import com.hudongwx.drawlottery.pojo.CommodityState;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/9 0009
 * Time: 2:30
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface IStateService {
    /**
     * 得到所有后台可用的类型
     *
     * @return 类型集合
     */
    public List<CommodityState> getAllState();
}
