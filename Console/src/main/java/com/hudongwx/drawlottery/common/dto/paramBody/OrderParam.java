package com.hudongwx.drawlottery.common.dto.paramBody;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/18 0018
 * Time: 10:07
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class OrderParam {
    /**
     * 期数搜索
     */
    private String roundTime;
    /**
     * 兑奖方式
     */
    private List<Integer> exchangeWay;
    /**
     * 商品属性
     */
    private List<Integer> genre;
    /**
     * 兑奖状态
     */
    private List<Integer> state;


}
