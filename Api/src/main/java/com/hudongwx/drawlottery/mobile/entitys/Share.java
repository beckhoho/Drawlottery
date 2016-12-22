package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_share")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 晒单发布日期
     */
    @Column(name = "issue_date")
    private Date issueDate;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 晒单详情（url）
     */
    private String particulars;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取晒单发布日期
     *
     * @return issue_date - 晒单发布日期
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * 设置晒单发布日期
     *
     * @param issueDate 晒单发布日期
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
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
     * 获取晒单详情（url）
     *
     * @return particulars - 晒单详情（url）
     */
    public String getParticulars() {
        return particulars;
    }

    /**
     * 设置晒单详情（url）
     *
     * @param particulars 晒单详情（url）
     */
    public void setParticulars(String particulars) {
        this.particulars = particulars == null ? null : particulars.trim();
    }
}