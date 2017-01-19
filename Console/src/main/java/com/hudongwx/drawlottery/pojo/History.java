package com.hudongwx.drawlottery.pojo;

import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.utils.SpringUtils;

public class History {
    private LangConstants langConstants = SpringUtils.getBean(LangConstants.class);

    private Long id;

    private Long commodityId;

    private Long empId;

    private String commodityName;

    private Integer genre;

    private String luckCode;

    private String roundTime;

    private Integer buyNumber;

    private Long endTime;

    private Long luckUserAccountId;

    private String coverImgUrl;

    private Integer buyTotalNumber;

    private Integer exchangeState;

    private Integer exchangeWay;

    private Integer cardNotEnough;

    private Integer shareState;

    /**
     * 兑奖方式的显示文字
     */
    private String exchangeWayLabel;

    /**
     * 兑奖状态显示文字
     */
    private String exchangeStateLabel;

    /**
     * 卡数不足显示文字
     */
    private String cardNotEnoughLabel;

    /**
     * 属性显示文字
     */
    private String genreLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getLuckCode() {
        return luckCode;
    }

    public void setLuckCode(String luckCode) {
        this.luckCode = luckCode == null ? null : luckCode.trim();
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime == null ? null : roundTime.trim();
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getLuckUserAccountId() {
        return luckUserAccountId;
    }

    public void setLuckUserAccountId(Long luckUserAccountId) {
        this.luckUserAccountId = luckUserAccountId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl == null ? null : coverImgUrl.trim();
    }

    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }

    public Integer getExchangeState() {
        return exchangeState;
    }

    public void setExchangeState(Integer exchangeState) {
        this.exchangeState = exchangeState;
    }

    public Integer getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(Integer exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public Integer getCardNotEnough() {
        return cardNotEnough;
    }

    public void setCardNotEnough(Integer cardNotEnough) {
        this.cardNotEnough = cardNotEnough;
    }

    public Integer getShareState() {
        return shareState;
    }

    public void setShareState(Integer shareState) {
        this.shareState = shareState;
    }

    public String getExchangeWayLabel() {
        return exchangeWayLabel;
    }

    public void setExchangeWayLabel(String exchangeWayLabel) {
        this.exchangeWayLabel = exchangeWayLabel;
    }

    public String getExchangeStateLabel() {
        exchangeStateLabel = langConstants.getLang(langConstants.EXCHANGE_STATE_NOT);
        if (1 == exchangeState)
            exchangeStateLabel = langConstants.getLang(langConstants.EXCHANGE_STATE_ON);
        return exchangeStateLabel;
    }

    public void setExchangeStateLabel(String exchangeStateLabel) {
        this.exchangeStateLabel = exchangeStateLabel;
    }

    public String getCardNotEnoughLabel() {
        cardNotEnoughLabel = "";
        if (cardNotEnough == 1)
            cardNotEnoughLabel = "卡数不足";
        return cardNotEnoughLabel;
    }

    public void setCardNotEnoughLabel(String cardNotEnoughLabel) {
        this.cardNotEnoughLabel = cardNotEnoughLabel;
    }

    public String getGenreLabel() {
        switch (genre) {
            case 1:
                genreLabel = "实体";
            case 2:
                genreLabel = "实体不可快递";
            default:
                genreLabel = "虚拟";
        }
        return genreLabel;
    }

    public void setGenreLabel(String genreLabel) {
        this.genreLabel = genreLabel;
    }
}