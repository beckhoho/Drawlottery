package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_commoditys")
public class Commoditys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 描述,详情
     */
    private String desc;

    /**
     * 商品类型id
     */
    @Column(name = "commodity_type_id")
    private Integer commodityTypeId;

    /**
     * 商品分类（1为实体，0为虚拟）
     */
    private Integer genre;

    /**
     * 当前购买次数
     */
    @Column(name = "buy_current_number")
    private Integer buyCurrentNumber;

    /**
     * 总购买次数
     */
    @Column(name = "buy_total_number")
    private Integer buyTotalNumber;

    /**
     * 商品开抢时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 幸运码id
     */
    @Column(name = "luck_code_id")
    private Integer luckCodeId;

    /**
     * 当前开奖状态（1为已开奖，0为未开奖，2为等待开奖）
     */
    private Integer state;

    /**
     * 商品期数
     */
    @Column(name = "round_time")
    private String roundTime;

    /**
     * 封面图片
     */
    @Column(name = "cover_img_id")
    private Integer coverImgId;

    /**
     * 是否自动生成下一期
     */
    @Column(name = "auto_round")
    private Integer autoRound;

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
     * 获取描述,详情
     *
     * @return desc - 描述,详情
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述,详情
     *
     * @param desc 描述,详情
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取商品类型id
     *
     * @return commodity_type_id - 商品类型id
     */
    public Integer getCommodityTypeId() {
        return commodityTypeId;
    }

    /**
     * 设置商品类型id
     *
     * @param commodityTypeId 商品类型id
     */
    public void setCommodityTypeId(Integer commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }

    /**
     * 获取商品分类（1为实体，0为虚拟）
     *
     * @return genre - 商品分类（1为实体，0为虚拟）
     */
    public Integer getGenre() {
        return genre;
    }

    /**
     * 设置商品分类（1为实体，0为虚拟）
     *
     * @param genre 商品分类（1为实体，0为虚拟）
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
     * 获取商品开抢时间
     *
     * @return start_time - 商品开抢时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置商品开抢时间
     *
     * @param startTime 商品开抢时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取幸运码id
     *
     * @return luck_code_id - 幸运码id
     */
    public Integer getLuckCodeId() {
        return luckCodeId;
    }

    /**
     * 设置幸运码id
     *
     * @param luckCodeId 幸运码id
     */
    public void setLuckCodeId(Integer luckCodeId) {
        this.luckCodeId = luckCodeId;
    }

    /**
     * 获取当前开奖状态（1为已开奖，0为未开奖，2为等待开奖）
     *
     * @return state - 当前开奖状态（1为已开奖，0为未开奖，2为等待开奖）
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置当前开奖状态（1为已开奖，0为未开奖，2为等待开奖）
     *
     * @param state 当前开奖状态（1为已开奖，0为未开奖，2为等待开奖）
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取商品期数
     *
     * @return round_time - 商品期数
     */
    public String getRoundTime() {
        return roundTime;
    }

    /**
     * 设置商品期数
     *
     * @param roundTime 商品期数
     */
    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime == null ? null : roundTime.trim();
    }

    /**
     * 获取封面图片
     *
     * @return cover_img_id - 封面图片
     */
    public Integer getCoverImgId() {
        return coverImgId;
    }

    /**
     * 设置封面图片
     *
     * @param coverImgId 封面图片
     */
    public void setCoverImgId(Integer coverImgId) {
        this.coverImgId = coverImgId;
    }

    /**
     * 获取是否自动生成下一期
     *
     * @return auto_round - 是否自动生成下一期
     */
    public Integer getAutoRound() {
        return autoRound;
    }

    /**
     * 设置是否自动生成下一期
     *
     * @param autoRound 是否自动生成下一期
     */
    public void setAutoRound(Integer autoRound) {
        this.autoRound = autoRound;
    }
}