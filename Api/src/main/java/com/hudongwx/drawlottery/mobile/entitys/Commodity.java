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
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_commoditys")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buy_last_number")
    private Integer buyLastNumber;

    @Column(name = "buy_current_number")
    private Integer buyCurrentNumber;

    @Column(name = "luck_code_id")
    private Long luckCodeId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBuyLastNumber() {
        return buyLastNumber;
    }

    public void setBuyLastNumber(Integer buyLastNumber) {
        this.buyLastNumber = buyLastNumber;
    }

    public Integer getBuyCurrentNumber() {
        return buyCurrentNumber;
    }

    public void setBuyCurrentNumber(Integer buyCurrentNumber) {
        this.buyCurrentNumber = buyCurrentNumber;
    }

    public Long getLuckCodeId() {
        return luckCodeId;
    }

    public void setLuckCodeId(Long luckCodeId) {
        this.luckCodeId = luckCodeId;
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
