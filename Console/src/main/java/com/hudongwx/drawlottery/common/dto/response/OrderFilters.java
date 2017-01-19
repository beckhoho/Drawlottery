package com.hudongwx.drawlottery.common.dto.response;

import com.hudongwx.drawlottery.common.dto.Node;

import java.util.List;

/**
 * 订单筛选条件.
 * Date: 2017/1/19 0019
 * Time: 17:35
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class OrderFilters {
    private List<Node> exchangeWay;
    private List<Node> genre;
    private List<Node> exchangeState;
    private List<Node> cardNotEnough;

    public List<Node> getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(List<Node> exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public List<Node> getGenre() {
        return genre;
    }

    public void setGenre(List<Node> genre) {
        this.genre = genre;
    }

    public List<Node> getExchangeState() {
        return exchangeState;
    }

    public void setExchangeState(List<Node> exchangeState) {
        this.exchangeState = exchangeState;
    }

    public List<Node> getCardNotEnough() {
        return cardNotEnough;
    }

    public void setCardNotEnough(List<Node> cardNotEnough) {
        this.cardNotEnough = cardNotEnough;
    }
}
