package com.hudongwx.drawlottery.mobile.entitys;


/**
 * Created by 11 on 2017/1/17.
 */
//消息通用父类
public class Notification {

    private Long id;

    /**
     * 用户ID
     */
    private Long userAccountId;

    /**
     * 活动通知标题
     */
    private String noticeTitle;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 发送时间
     */
    private Long sendDate;

    /**
     * 消息状态
     */
    private int state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setSendDate(Long sendDate) {

        this.sendDate = sendDate;
    }
}
