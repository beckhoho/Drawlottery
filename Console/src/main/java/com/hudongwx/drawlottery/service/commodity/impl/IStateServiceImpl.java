package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.dao.CommodityStateMapper;
import com.hudongwx.drawlottery.pojo.CommodityState;
import com.hudongwx.drawlottery.service.commodity.IStateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/9 0009
 * Time: 2:32
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("stateService")
public class IStateServiceImpl implements IStateService {
    @Resource
    private CommodityStateMapper commodityStateMapper;
    /**
     * 得到所有后台可用的类型
     *
     * @return 类型集合
     */
    @Override
    public List<CommodityState> getAllState() {
        return commodityStateMapper.selectAll();
    }
}
