package com.hudongwx.drawlottery.pojo;

import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.utils.SpringUtils;
import com.hudongwx.drawlottery.common.utils.toolbox.DateUtil;

import java.util.Date;

public class History {
    private LangConstants langConstants = SpringUtils.getBean(LangConstants.class);
    private CommonConstants commonConstants = SpringUtils.getBean(CommonConstants.class);
    private Long id;

    private Long commodityId;

    private Long empId;

    private String commodityName;


    private String luckCode;

    private String roundTime;

    private Integer buyNumber;


    private Long luckUserAccountId;

    private String coverImgUrl;

    private Integer buyTotalNumber;


    private Integer cardNotEnough;

    private Integer shareState;

    private Integer exchangeWay;
    /**
     * 兑奖方式的显示文字
     */
    private String exchangeWayLabel;

    private Integer exchangeState;
    /**
     * 兑奖状态显示文字
     */
    private String exchangeStateLabel;

    /**
     * 卡数不足显示文字
     */
    private String cardNotEnoughLabel;

    private Integer genre;
    /**
     * 属性显示文字
     */
    private String genreLabel;

    private Long endTime;
    /**
     * 揭晓时间显示文字，格式化时间后的字符串
     */
    private String endTimeLabel;


    //-------------------详情字段------------------------//
    /**
     * 地址
     */
    private String address;
    /**
     * 快递状态
     */
    private Integer expressState;

    /**
     * 发货信息id
     */
    private Long expressId;
    /**
     * 快递名
     */
    private String deliveryName;
    /**
     * 快递单号
     */
    private String deliveryNumber;
    /**
     * 收件人姓名
     */
    private String receiverName;
    /**
     * 收件人电话
     */
    private String receiverPhone;

    /**
     * 用户姓名
     */
    private String nickname;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户电话
     */
    private String userPhoneNumber;


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
        if (null == exchangeState)
            return commonConstants.getEmptyString();
        if (1 == exchangeState)
            exchangeStateLabel = langConstants.getLang(langConstants.EXCHANGE_STATE_ON);
        return exchangeStateLabel;
    }

    public void setExchangeStateLabel(String exchangeStateLabel) {
        this.exchangeStateLabel = exchangeStateLabel;
    }

    public String getCardNotEnoughLabel() {
        cardNotEnoughLabel = "";
        if (null == cardNotEnough)
            return commonConstants.getEmptyString();
        if (cardNotEnough == 1)
            cardNotEnoughLabel = "卡数不足";
        return cardNotEnoughLabel;
    }

    public void setCardNotEnoughLabel(String cardNotEnoughLabel) {
        this.cardNotEnoughLabel = cardNotEnoughLabel;
    }

    public String getGenreLabel() {
        return commonConstants.convertGenre(genre);
    }

    public void setGenreLabel(String genreLabel) {
        this.genreLabel = genreLabel;
    }

    public String getEndTimeLabel() {
        if (null == endTime)
            return commonConstants.getEmptyString();
        return DateUtil.format(new Date(endTime));
    }

    public void setEndTimeLabel(String endTimeLabel) {
        this.endTimeLabel = endTimeLabel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getExpressState() {
        return expressState;
    }

    public void setExpressState(Integer expressState) {
        this.expressState = expressState;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }
}