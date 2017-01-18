package com.hudongwx.drawlottery.common.dto.paramBody;

import java.util.List;

/**
 * 历史参数.
 * Date: 2017/1/18 0018
 * Time: 10:07
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class HistoryParam {
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

    /**
     * 发卡数量是否充足
     */
    private List<Integer> cardNotEnough;


    public HistoryParam(String roundTime, List<Integer> exchangeWay, List<Integer> genre, List<Integer> state, List<Integer> cardNotEnough) {
        this.roundTime = roundTime;
        this.exchangeWay = exchangeWay;
        this.genre = genre;
        this.state = state;
        this.cardNotEnough = cardNotEnough;
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }

    public List<Integer> getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(List<Integer> exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public List<Integer> getState() {
        return state;
    }

    public void setState(List<Integer> state) {
        this.state = state;
    }

    public List<Integer> getCardNotEnough() {
        return cardNotEnough;
    }

    public void setCardNotEnough(List<Integer> cardNotEnough) {
        this.cardNotEnough = cardNotEnough;
    }
}
