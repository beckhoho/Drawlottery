package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_notification_prize")
public class NotificationPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户accountID
     */
    @Column(name = "user_account_id")
    private Long accountId;

    /**
     *商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 幸运码ID
     */
    @Column(name = "luck_codes_id")
    private Long luckCodesId;

    /**
     * 开奖时间
     */
    @Column(name = "on_prize_date")
    private Date onPrizeDate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getLuckCodesId() {
        return luckCodesId;
    }

    public void setLuckCodesId(Long luckCodesId) {
        this.luckCodesId = luckCodesId;
    }

    public Date getOnPrizeDate() {
        return onPrizeDate;
    }

    public void setOnPrizeDate(Date onPrizeDate) {
        this.onPrizeDate = onPrizeDate;
    }
}