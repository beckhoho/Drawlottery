package com.hudongwx.drawlottery.common.dto.paramBody;

import java.io.Serializable;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/16 0016
 * Time: 2:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class CardParam implements Serializable {
    public static Long serialVersionUID = 1L;
    private List<Integer> corporation;
    private int order;
    private int  direction;

    public List<Integer> getCorporation() {
        return corporation;
    }

    public void setCorporation(List<Integer> corporation) {
        this.corporation = corporation;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
