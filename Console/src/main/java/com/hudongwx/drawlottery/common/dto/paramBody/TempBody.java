package com.hudongwx.drawlottery.common.dto.paramBody;

import com.hudongwx.drawlottery.common.base.PoParamBase;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/10 0010
 * Time: 14:32
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class TempBody implements PoParamBase<CommodityTemplate> {

    @Resource
    private LangConstants langConstants;

    private Long id;
    private String name;
    private Long type;
    private Integer genre;
    private String contactName;
    private String contactPhone;
    private String contactAddress;

    private Boolean express;

    private Boolean sendCard;
    private Integer cardType;
    private Integer cardNum;
    private Integer cardMoney;

    private Boolean withdrawals;
    private Integer withdrawalsMoney;

    private Boolean exchangeable;
    private Integer exchangeMoney;

    private String coverImgUrl;
    private String content;
    private Boolean easyWinning;
    private Boolean autoRound;
    private Integer buyTotalNumber;
    private Integer minimum;
    private Integer openTime;
    private Date groundTime;
    private List<Integer> exchangeWay;

    private List<String> images;

    /**
     * 作为实体类的po参数包，需要有打包自己的成为一个实体类的能力.
     *
     * @return 封装后的商品对象
     */
    @Override
    public CommodityTemplate packingMe() {
        CommodityTemplate c = new CommodityTemplate();
        c.setId(id);
        c.setName(name);
        c.setCommodityTypeId(type);
        c.setGenre(genre);

        c.setContactName(contactName);
        c.setContactPhone(contactPhone);
        c.setContactAddress(contactAddress);

        c.setCardType(cardType);
        c.setCardNum(cardNum);
        c.setCardMoney(cardMoney);

        c.setWithdrawalsMoney(withdrawalsMoney);

        c.setExchangeMoney(exchangeMoney);

        c.setCoverImgUrl(coverImgUrl);

        c.setContent(content);

        c.setBuyTotalNumber(buyTotalNumber);

        c.setMinimum(minimum);

        c.setOpenTime(openTime);
        if (groundTime != null)
            c.setGroundTime(groundTime.getTime());

        c.setAutoRound(autoRound ? 1 : 0);
        c.setEasyWinning(easyWinning ? 1 : 0);
        exchangeWay = new ArrayList<>();
        if (sendCard != null && sendCard)
            exchangeWay.add(1);
        if (express != null && express)
            exchangeWay.add(2);
        if (withdrawals != null && withdrawals)
            exchangeWay.add(3);
        if (null != exchangeable && exchangeable)
            exchangeWay.add(4);
        if (null != genre && genre == 2)
            exchangeWay.add(5);

        c.setExchangeWay(exchangeWay);
        c.setImages(images);

        return c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Boolean getSendCard() {
        return sendCard;
    }

    public void setSendCard(Boolean sendCard) {
        this.sendCard = sendCard;
    }

    public Integer getCardType() {
        return cardType;
    }

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

    public Boolean getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(Boolean withdrawals) {
        this.withdrawals = withdrawals;
    }

    public Integer getWithdrawalsMoney() {
        return withdrawalsMoney;
    }

    public void setWithdrawalsMoney(Integer withdrawalsMoney) {
        this.withdrawalsMoney = withdrawalsMoney;
    }

    public Boolean getExchangeable() {
        return exchangeable;
    }

    public void setExchangeable(Boolean exchangeable) {
        this.exchangeable = exchangeable;
    }

    public Integer getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(Integer exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getEasyWinning() {
        return easyWinning;
    }

    public void setEasyWinning(Boolean easyWinning) {
        this.easyWinning = easyWinning;
    }

    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }

    public Date getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(Date groundTime) {
        this.groundTime = groundTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAutoRound() {
        return autoRound;
    }

    public void setAutoRound(Boolean autoRound) {
        this.autoRound = autoRound;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Integer> getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(List<Integer> exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public Boolean getExpress() {
        return express;
    }

    public void setExpress(Boolean express) {
        this.express = express;
    }
}
