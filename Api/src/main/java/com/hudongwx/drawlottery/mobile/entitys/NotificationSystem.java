package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 14:08　<br/>
 * <p>
 * 系统通知实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_notification_system")
public class NotificationSystem extends Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 系统通知标题
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
     * 通知详情url
     */
    @Column(name = "notice_url")
    private String noticeUrl;

    /**
     * 发送时间
     */
    @Column(name = "send_date")
    private Long sendDate;

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

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }
}
