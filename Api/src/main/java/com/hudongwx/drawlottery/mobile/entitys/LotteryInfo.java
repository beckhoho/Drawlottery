package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 11 on 2017/1/14.
 */
@Table(name = "t_lottery_info")
public class LotteryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 中奖幸运码
     */
    @Column(name = "lottery_id")
    private Long lotteryId;

    /**
     * json：购买最后五十商品的用户昵称，日期，毫秒数
     */
    @Column(name = "lottery_info")
    private String lotteryInfo;


    /**
     * 最后五十商品毫秒和
     */
    @Column(name = "sum_date")
    private Long sumDate;

    /**
     * 商品购买总人次
     */
    @Column(name = "buy_num")
    private int buyNum;

    @Column(name = "user_account_id")
    private Long userAccountId;

    @Column(name = "end_date")
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

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

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }
}
