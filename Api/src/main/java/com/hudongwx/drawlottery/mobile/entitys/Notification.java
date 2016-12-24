package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 通知时间
     */
    @Column(name = "noction_date")
    private Date noctionDate;

    /**
     * 通知title
     */
    @Column(name = "noction_title")
    private String noctionTitle;

    /**
     * 通知详情（URL）
     */
    @Column(name = "noction_particulars")
    private String noctionParticulars;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取通知时间
     *
     * @return noction_date - 通知时间
     */
    public Date getNoctionDate() {
        return noctionDate;
    }

    /**
     * 设置通知时间
     *
     * @param noctionDate 通知时间
     */
    public void setNoctionDate(Date noctionDate) {
        this.noctionDate = noctionDate;
    }

    /**
     * 获取通知title
     *
     * @return noction_title - 通知title
     */
    public String getNoctionTitle() {
        return noctionTitle;
    }

    /**
     * 设置通知title
     *
     * @param noctionTitle 通知title
     */
    public void setNoctionTitle(String noctionTitle) {
        this.noctionTitle = noctionTitle == null ? null : noctionTitle.trim();
    }

    /**
     * 获取通知详情（URL）
     *
     * @return noction_particulars - 通知详情（URL）
     */
    public String getNoctionParticulars() {
        return noctionParticulars;
    }

    /**
     * 设置通知详情（URL）
     *
     * @param noctionParticulars 通知详情（URL）
     */
    public void setNoctionParticulars(String noctionParticulars) {
        this.noctionParticulars = noctionParticulars == null ? null : noctionParticulars.trim();
    }
}