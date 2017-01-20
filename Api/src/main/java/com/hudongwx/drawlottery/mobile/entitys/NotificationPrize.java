package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_notification_prize")
public class NotificationPrize extends Notification{
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
     * 活动通知标题
     */
    @Column(name = "notice_title")
    private String noticeTitle;

    /**
     * 通知内容
     */
    @Column(name="notice_content")
    private String noticeContent;

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    /**
     * 发送时间
     */
    @Column(name = "send_date")
    private Long sendDate;

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 幸运码ID
     */
    @Column(name = "luck_codes_id")
    private Long luckCodesId;

    /**
     * 开奖时间
     */
    @Column(name = "on_prize_date")
    private Long onPrizeDate;

    @Column(name = "luck_account_id")
    private Long luckAccountId;

    /**
     * 阅读状态  0:未读   1：已读
     */
    @Column(name = "state")
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public Long getLuckAccountId() {
        return luckAccountId;
    }

    public void setLuckAccountId(Long luckAccountId) {
        this.luckAccountId = luckAccountId;
    }

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

    public Long getOnPrizeDate() {
        return onPrizeDate;
    }

    public void setOnPrizeDate(Long onPrizeDate) {
        this.onPrizeDate = onPrizeDate;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
}