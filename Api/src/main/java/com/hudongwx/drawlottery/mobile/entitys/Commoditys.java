package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_commoditys")
public class Commoditys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private Long id;

    /**
     * 商品名
     */
    @Transient
    private String name;

    /**
     * 商品类型id
     */
    @Column(name = "commodity_type_id")
    private Long commodityTypeId;

    /**
     * 商品类别（1：实体，0：虚拟）
     */
    private Integer genre;

    /**
     * 当前购买次数
     */
    @Column(name = "buy_current_number")
    private Integer buyCurrentNumber;


    /**
     * 卡类型
     */
    @Column(name = "card_type")
    private Integer cardType;

    /**
     * 卡数量
     */
    @Column(name = "card_num")
    private Integer cardNum;

    /**
     * 卡面额
     */
    @Column(name = "card_money")
    private Integer cardMoney;


    /**
     * 总购买次数
     */
    @Column(name = "buy_total_number")
    private Integer buyTotalNumber;

    /**
     * 开抢时间
     */
    @Column(name = "ground_time")
    private Long groundTime;


    /**
     * 商品状态ID
     */
    @Column(name = "state_id")
    private Integer stateId;

    /**
     * 当前期数
     */
    @Column(name = "round_time")
    private String roundTime;

    /**
     * 封面图片id
     */
    @Column(name = "cover_img_url")
    private String coverImgUrl;

    /**
     * 是否自动生成下一期（1：是，0：否）
     */
    @Column(name = "auto_round")
    private Integer autoRound;

    /**
     * 商品详情URL
     */
    @Column(name = "commodity_desc_url")
    private String commodityDescUrl;

    /**
     * 商品描述
     */
    @Column(name = "commodity_desc")
    private String commodityDesc;

    /**
     * 售罄时间
     */
    @Column(name = "sell_out_time")
    private Long sellOutTime;

    /**
     * 下架时间
     */
    @Column(name = "undercarriage_time")
    private Long undercarriageTime;

    /**
     * 点击量
     */
    @Column(name = "view_num")
    private Long viewNum;

    /**
     * 模板ID
     */
    @Column(name = "temp_id")
    private Long tempId;

    /**
     * 折换现金
     */
    @Column(name = "withdrawals_money")
    private Integer withdrawalsMoney;

    /**
     * 折换闪币
     */
    @Column(name = "exchange_money")
    private Integer exchangeMoney;

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(Integer cardMoney) {
        this.cardMoney = cardMoney;
    }

    public Integer getWithdrawalsMoney() {
        return withdrawalsMoney;
    }

    public void setWithdrawalsMoney(Integer withdrawalsMoney) {
        this.withdrawalsMoney = withdrawalsMoney;
    }

    public Integer getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(Integer exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
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

    /**
     * 是否可用
     */
    private Integer valid;

    /**
     * 最低购买量
     */
    private Integer minimum;

    public Long getUndercarriageTime() {
        return undercarriageTime;
    }

    public void setUndercarriageTime(Long undercarriageTime) {
        this.undercarriageTime = undercarriageTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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
     * 获取商品名
     *
     * @return name - 商品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名
     *
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品类型id
     *
     * @return commodity_type_id - 商品类型id
     */
    public Long getCommodityTypeId() {
        return commodityTypeId;
    }

    /**
     * 设置商品类型id
     *
     * @param commodityTypeId 商品类型id
     */
    public void setCommodityTypeId(Long commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }

    /**
     * 获取商品类别（1：实体，0：虚拟）
     *
     * @return genre - 商品类别（1：实体，0：虚拟）
     */
    public Integer getGenre() {
        return genre;
    }

    /**
     * 设置商品类别（1：实体，0：虚拟）
     *
     * @param genre 商品类别（1：实体，0：虚拟）
     */
    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    /**
     * 获取当前购买次数
     *
     * @return buy_current_number - 当前购买次数
     */
    public Integer getBuyCurrentNumber() {
        return buyCurrentNumber;
    }

    /**
     * 设置当前购买次数
     *
     * @param buyCurrentNumber 当前购买次数
     */
    public void setBuyCurrentNumber(Integer buyCurrentNumber) {
        this.buyCurrentNumber = buyCurrentNumber;
    }

    /**
     * 获取总购买次数
     *
     * @return buy_total_number - 总购买次数
     */
    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    /**
     * 设置总购买次数
     *
     * @param buyTotalNumber 总购买次数
     */
    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }

    /**
     * 获取开抢时间
     *
     * @return startTime - 开抢时间
     */
    public Long getGroundTime() {
        return groundTime;
    }

    /**
     * 设置开抢时间
     *
     * @param groundTime 开抢时间
     */
    public void setGroundTime(Long groundTime) {
        this.groundTime = groundTime;
    }


    /**
     * 获取当前中奖状态（1：已开奖，0：未开奖）
     *
     * @return state - 当前中奖状态（1：已开奖，0：未开奖）
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * 设置当前中奖状态（1：已开奖，0：未开奖）
     *
     * @param state 当前中奖状态（1：已开奖，0：未开奖）
     */
    public void setStateId(Integer state) {
        this.stateId = state;
    }

    /**
     * 获取当前期数
     *
     * @return round_time - 当前期数
     */
    public String getRoundTime() {
        return roundTime;
    }

    /**
     * 设置当前期数
     *
     * @param roundTime 当前期数
     */
    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime == null ? null : roundTime.trim();
    }

    /**
     * 获取封面图片id
     *
     * @return cover_img_id - 封面图片id
     */
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    /**
     * 设置封面图片id
     *
     * @param coverImgId 封面图片id
     */
    public void setCoverImgUrl(String coverImgId) {
        this.coverImgUrl = coverImgId;
    }

    /**
     * 获取是否自动生成下一期（1：是，0：否）
     *
     * @return auto_round - 是否自动生成下一期（1：是，0：否）
     */
    public Integer getAutoRound() {
        return autoRound;
    }

    /**
     * 设置是否自动生成下一期（1：是，0：否）
     *
     * @param autoRound 是否自动生成下一期（1：是，0：否）
     */
    public void setAutoRound(Integer autoRound) {
        this.autoRound = autoRound;
    }

    /**
     * 获取商品详情URL
     *
     * @return commodity_desc_url - 商品详情URL
     */
    public String getCommodityDescUrl() {
        return commodityDescUrl;
    }

    /**
     * 设置商品详情URL
     *
     * @param commodityDescUrl 商品详情URL
     */
    public void setCommodityDescUrl(String commodityDescUrl) {
        this.commodityDescUrl = commodityDescUrl == null ? null : commodityDescUrl.trim();
    }

    /**
     * 获取商品描述
     *
     * @return desc - 商品描述
     */
    public String getDesc() {
        return commodityDesc;
    }

    /**
     * 设置商品描述
     *
     * @param desc 商品描述
     */
    public void setDesc(String desc) {
        this.commodityDesc = desc == null ? null : desc.trim();
    }

    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc;
    }

    public Long getSellOutTime() {
        return sellOutTime;
    }

    public void setSellOutTime(Long sellOutTime) {
        this.sellOutTime = sellOutTime;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }
}