package com.hudongwx.drawlottery.mobile.entitys;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.Table;

/**
 * Created by 11 on 2017/1/14.
 */
public class LotteryInfo {
    private Long id;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 中奖幸运码
     */
    private Long lotteryId;

    /**
     * json：中奖者昵称，日期，毫秒数
     */
    private String lotteryInfo;

    /**
     * 最后五十商品毫秒和
     */
    private Long sumDate;

    /**
     * 商品购买总人次
     */
    private Long buyNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getLotteryInfo() {
        return lotteryInfo;
    }

    public void setLotteryInfo(String lotteryInfo) {
        this.lotteryInfo = lotteryInfo;
    }

    public Long getSumDate() {
        return sumDate;
    }

    public void setSumDate(Long sumDate) {
        this.sumDate = sumDate;
    }

    public Long getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Long buyNum) {
        this.buyNum = buyNum;
    }
}
