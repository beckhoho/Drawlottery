package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_commodity_history")
public class CommodityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 幸运号id
     */
    @Column(name = "luck_code")
    private Long luckCode;

    /**
     * 商品名
     */
    @Column(name = "commodity_name")
    private String commodityName;

    /**
     * 商品得主id
     */
    @Column(name = "user_account")
    private Long userAccount;

    /**
     * 期数
     */
    @Column(name = "round_time")
    private Long roundTime;

    /**
     * 购买数量
     */
    @Column(name = "buy_number")
    private Integer buyNumber;

    /**
     * 商品封面图
     */
    @Column(name = "cover_img_url")
    private String coverImgUrl;

    /**
     * 揭晓时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 本期中奖用户
     */
    @Column(name = "luck_user_account_id")
    private Long luckUserAccountId;

    /**
     * 本期总需人数
     */
    @Column(name = "buy_total_number")
    private Integer buyTotalNumber;

    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Long getLuckUserAccountId() {
        return luckUserAccountId;
    }

    public void setLuckUserAccountId(Long luckUserAccountId) {
        this.luckUserAccountId = luckUserAccountId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

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
     * 获取幸运号id
     *
     * @return luck_code - 幸运号id
     */
    public Long getLuckCode() {
        return luckCode;
    }

    /**
     * 设置幸运号id
     *
     * @param luckCodeId 幸运号id
     */
    public void setLuckCode(Long luckCodeId) {
        this.luckCode = luckCodeId;
    }

    /**
     * 获取商品得主id
     *
     * @return user_account_id - 商品得主id
     */
    public Long getUserAccount() {
        return userAccount;
    }

    /**
     * 设置商品得主id
     *
     * @param userAccountId 商品得主id
     */
    public void setUserAccount(Long userAccountId) {
        this.userAccount = userAccountId;
    }

    /**
     * 获取期数
     *
     * @return round_time - 期数
     */
    public Long getRoundTime() {
        return roundTime;
    }

    /**
     * 设置期数
     *
     * @param roundTime 期数
     */
    public void setRoundTime(Long roundTime) {
        this.roundTime = roundTime;
    }

    /**
     * 获取购买数量
     *
     * @return buy_number - 购买数量
     */
    public Integer getBuyNumber() {
        return buyNumber;
    }

    /**
     * 设置购买数量
     *
     * @param buyNumber 购买数量
     */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
}