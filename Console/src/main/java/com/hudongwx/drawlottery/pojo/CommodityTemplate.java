package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;
import java.util.List;

@Table(name = "t_commodity_template")
public class CommodityTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品描述
     */
    @Column(name = "commodity_desc")
    private String commodityDesc;

    /**
     * 商品类型id
     */
    @Column(name = "commodity_type_id")
    private Long commodityTypeId;
    /**
     * 商品类型
     */
    private String typeName;

    /**
     * 商品类别（1：实体，0：虚拟，2、实体不可快递）
     */
    private Integer genre;

    /**
     * 总购买人次
     */
    @Column(name = "buy_total_number")
    private Integer buyTotalNumber;

    /**
     * 上架时间
     */
    @Column(name = "ground_time")
    private Long groundTime;

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
     * 是否可用
     */
    @Column(name = "valid")
    private Integer valid;

    /**
     * 最低购买量
     */
    private Integer minimum;

    /**
     * 下架时间
     */
    @Column(name = "undercarriage_time")
    private Long undercarriageTime;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "card_type")
    private Integer cardType;

    @Column(name = "card_num")
    private Integer cardNum;

    @Column(name = "card_money")
    private Integer cardMoney;

    @Column(name = "easyWinning")
    private Integer easyWinning;

    @Column(name = "withdrawals_money")
    private Integer withdrawalsMoney;


    @Column(name = "exchange_money")
    private Integer exchangeMoney;

    @Column(name = "content")
    private String content;

    @Column(name = "open_time")
    private Integer openTime;
    @Transient
    private List<Integer> exchangeWay;
    @Transient
    private List<String> images;

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
     * 获取商品描述
     *
     * @return commodity_desc - 商品描述
     */
    public String getCommodityDesc() {
        return commodityDesc;
    }

    /**
     * 设置商品描述
     *
     * @param commodityDesc 商品描述
     */
    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc == null ? null : commodityDesc.trim();
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
     * 获取商品类别（1：实体，0：虚拟，2、实体不可快递）
     *
     * @return genre - 商品类别（1：实体，0：虚拟，2、实体不可快递）
     */
    public Integer getGenre() {
        return genre;
    }

    /**
     * 设置商品类别（1：实体，0：虚拟，2、实体不可快递）
     *
     * @param genre 商品类别（1：实体，0：虚拟，2、实体不可快递）
     */
    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    /**
     * 获取总购买人次
     *
     * @return buy_total_number - 总购买人次
     */
    public Integer getBuyTotalNumber() {
        return buyTotalNumber;
    }

    /**
     * 设置总购买人次
     *
     * @param buyTotalNumber 总购买人次
     */
    public void setBuyTotalNumber(Integer buyTotalNumber) {
        this.buyTotalNumber = buyTotalNumber;
    }

    /**
     * 获取上架时间
     *
     * @return ground_time - 上架时间
     */
    public Long getGroundTime() {
        return groundTime;
    }

    /**
     * 设置上架时间
     *
     * @param groundTime 上架时间
     */
    public void setGroundTime(Long groundTime) {
        this.groundTime = groundTime;
    }


    /**
     * 获取封面图片id
     *
     * @return cover_img_url - 封面图片id
     */
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    /**
     * 设置封面图片id
     *
     * @param coverImgUrl 封面图片id
     */
    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl == null ? null : coverImgUrl.trim();
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
     * 获取是否可用
     *
     * @return valid - 是否可用
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置是否可用
     *
     * @param valid 是否可用
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * 获取最低购买量
     *
     * @return minimum - 最低购买量
     */
    public Integer getMinimum() {
        return minimum;
    }

    /**
     * 设置最低购买量
     *
     * @param minimum 最低购买量
     */
    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    /**
     * 获取下架时间
     *
     * @return undercarriage_time - 下架时间
     */
    public Long getUndercarriageTime() {
        return undercarriageTime;
    }

    /**
     * 设置下架时间
     *
     * @param undercarriageTime 下架时间
     */
    public void setUndercarriageTime(Long undercarriageTime) {
        this.undercarriageTime = undercarriageTime;
    }

    /**
     * @return contact_name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * @return contact_phone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * @return contact_address
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * @param contactAddress
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    /**
     * @return cart_type
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return card_num
     */
    public Integer getCardNum() {
        return cardNum;
    }

    /**
     * @param cardNum
     */
    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * @return card_money
     */
    public Integer getCardMoney() {
        return cardMoney;
    }

    public Integer getWithdrawalsMoney() {
        return withdrawalsMoney;
    }

    public void setWithdrawalsMoney(Integer withdrawalsMoney) {
        this.withdrawalsMoney = withdrawalsMoney;
    }

    /**
     * @param cardMoney
     */
    public void setCardMoney(Integer cardMoney) {
        this.cardMoney = cardMoney;
    }

    /**
     * @return esayWinning
     */
    public Integer getEasyWinning() {
        return easyWinning;
    }

    /**
     * @param easyWinning
     */
    public void setEasyWinning(Integer easyWinning) {
        this.easyWinning = easyWinning;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(Integer exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Integer> getExchangeWay() {
        return exchangeWay;
    }

    public void setExchangeWay(List<Integer> exchangeWay) {
        this.exchangeWay = exchangeWay;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}