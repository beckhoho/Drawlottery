package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_commodity_history")
public class CommodityHistory {

    /**
     * 商品id
     */
    @Id
    @Column(name = "commodity_id")
    private Long commodityId;


    /**
     * 模板ID
     */
    @Column(name = "temp_id")
    private Long tempId;


    /**
     * 期数
     */
    @Column(name = "round_time")
    private String roundTime;

    /**
     * 购买数量
     */
    @Column(name = "buy_number")
    private Integer buyNumber;


    /**
     * 揭晓时间
     */
    @Column(name = "end_time")
    private Long endTime;



    /**
     * 兑换状态
     */
    @Column(name = "exchange_state")
    private Integer exchangeState;

    /**
     * 已选择兑奖方式id
     */
    @Column(name = "exchange_way")
    private Integer exchangeWay;

    /**
     * 晒单状态
     */
    @Column(name="share_state")
    private Integer shareState;


    public Integer getShareState() {
        return shareState;
    }

    public void setShareState(Integer shareState) {
        this.shareState = shareState;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Integer getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(Integer exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public Integer getExchangeState() {
        return exchangeState;
    }

    public void setExchangeState(Integer exchangeState) {
        this.exchangeState = exchangeState;
    }



    /**
     * 本期总需人数
     */
    private Integer genre;


    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


    /**
     * 获取商品id
     *
     * @return commodity_id - 商品id
     */
    public Long getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品id
     *
     * @param commodityId 商品id
     */
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }


    /**
     * 获取期数
     *
     * @return round_time - 期数
     */
    public String getRoundTime() {
        return roundTime;
    }

    /**
     * 设置期数
     *
     * @param roundTime 期数
     */
    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }

    /**
     * 获取购买数量
     *
     * @return buy_number - 购买数量
     */
    public Integer getBuyNumber() {
        return buyNumber;
    }

    /**
     * 设置购买数量
     *
     * @param buyNumber 购买数量
     */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }
}