package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/14 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/14 18:48　<br/>
 * <p>
 *        商品表（用作生成下一期，和写入历史信息用）
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_commoditys")
public class Commodity {

    public static final Integer SELL_OUT = 2;
    public static final int ON_SELL = 3;
    public static final int WILL_SELL = 4;
    public Commoditys castConvert(){
        final Commoditys commoditys = new Commoditys();
        commoditys.setSellOutTime(this.sellOutTime);
        commoditys.setStateId(this.stateId);
        commoditys.setBuyCurrentNumber(this.buyCurrentNumber);
        commoditys.setId(this.id);
        commoditys.setRoundTime(this.roundTime);
        commoditys.setViewNum(this.viewNum);
        commoditys.setTempId(this.tempId);
        commoditys.setUndercarriageTime(this.undercarriageTime);
        return commoditys;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private Long id;

    @Column(name = "buy_current_number")
    private Integer buyCurrentNumber;

    @Column(name = "round_time")
    private String roundTime;

    @Column(name = "sell_out_time")
    private Long sellOutTime;

    @Column(name = "view_num")
    private Long viewNum;

    @Column(name = "temp_id")
    private Long tempId;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "undercarriage_time")
    private Long undercarriageTime;

    @Column(name = "share_state")
    private Integer shareState;

    @Column(name = "exchange_state")
    private Integer exchangeState;

    @Column(name = "exchange_way")
    private Integer exchangeWay;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "card_not_enough")
    private Integer cardNotEnough;

    @Column(name = "buy_number")
    private Integer buyNumber;

    public Integer getShareState() {
        return shareState;
    }

    public void setShareState(Integer shareState) {
        this.shareState = shareState;
    }

    public Integer getExchangeState() {
        return exchangeState;
    }

    public void setExchangeState(Integer exchangeState) {
        this.exchangeState = exchangeState;
    }

    public Integer getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(Integer exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getCardNotEnough() {
        return cardNotEnough;
    }

    public void setCardNotEnough(Integer cardNotEnough) {
        this.cardNotEnough = cardNotEnough;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBuyCurrentNumber() {
        return buyCurrentNumber;
    }

    public void setBuyCurrentNumber(Integer buyCurrentNumber) {
        this.buyCurrentNumber = buyCurrentNumber;
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }

    public Long getSellOutTime() {
        return sellOutTime;
    }

    public void setSellOutTime(Long sellOutTime) {
        this.sellOutTime = sellOutTime;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Long getUndercarriageTime() {
        return undercarriageTime;
    }

    public void setUndercarriageTime(Long undercarriageTime) {
        this.undercarriageTime = undercarriageTime;
    }
}
