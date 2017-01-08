package com.hudongwx.drawlottery.common.dto.response;

import com.hudongwx.drawlottery.pojo.CommodityState;
import com.hudongwx.drawlottery.pojo.CommodityType;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/9 0009
 * Time: 2:37
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class CommodityFiltersResult {
    private List<CommodityState> states;
    private List<CommodityType> types;

    public List<CommodityState> getStates() {
        return states;
    }

    public void setStates(List<CommodityState> states) {
        this.states = states;
    }

    public List<CommodityType> getTypes() {
        return types;
    }

    public void setTypes(List<CommodityType> types) {
        this.types = types;
    }
}
