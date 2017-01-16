package com.hudongwx.drawlottery.common.dto.paramBody;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/16 0016
 * Time: 2:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class CardParam {
    private List<Integer> corporation;
    private Integer order;
    private Integer  direction;

    public List<Integer> getCorporation() {
        return corporation;
    }

    public void setCorporation(List<Integer> corporation) {
        this.corporation = corporation;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
}
