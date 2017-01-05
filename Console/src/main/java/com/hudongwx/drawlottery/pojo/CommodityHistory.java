package com.hudongwx.drawlottery.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_commodity_history")
public class CommodityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名
     */
    @Column(name = "commodity_name")
    private String commodityName;

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
     * 参与用户id
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
     * 揭晓时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 本期中奖用户ID
     */
    @Column(name = "luck_user_account_id")
    private Long luckUserAccountId;

    /**
     * 封面图URL
     */
    @Column(name = "cover_img_url")
    private String coverImgUrl;

    /**
     * 当期总需人数
     */
    @Column(name = "buy_total_number")
    private Integer buyTotalNumber;

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
     * 获取商品名
     *
     * @return commodity_name - 商品名
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * 设置商品名
     *
     * @param commodityName 商品名
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
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
     * @param luckCode 幸运号id
     */
    public void setLuckCode(Long luckCode) {
        this.luckCode = luckCode;
    }

    /**
     * 获取参与用户id
     *
     * @return user_account - 参与用户id
     */
    public Long getUserAccount() {
        return userAccount;
    }

    /**
     * 设置参与用户id
     *
     * @param userAccount 参与用户id
     */
    public void setUserAccount(Long userAccount) {
        this.userAccount = userAccount;
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

    /**
     * 获取揭晓时间
     *
     * @return end_time - 揭晓时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置揭晓时间
     *
     * @param endTime 揭晓时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取本期中奖用户ID
     *
     * @return luck_user_account_id - 本期中奖用户ID
     */
    public Long getLuckUserAccountId() {
        return luckUserAccountId;
    }

    /**
     * 设置本期中奖用户ID
     *
     * @param luckUserAccountId 本期中奖用户ID
     */
    public void setLuckUserAccountId(Long luckUserAccountId) {
        this.luckUserAccountId = luckUserAccountId;
    }

    /**
     * 获取封面图URL
     *
     * @return cover_img_url - 封面图URL
     */
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    /**
     * 设置封面图URL
     *
     * @param coverImgUrl 封面图URL
     */
    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl == null ? null : coverImgUrl.trim();
    }

    /**
     * 获取当期总需人数
     *
     * @return buy_total_number - 当期总需人数
     */
    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    /**
     * 设置当期总需人数
     *
     * @param buyTotalNumber 当期总需人数
     */
    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }
}