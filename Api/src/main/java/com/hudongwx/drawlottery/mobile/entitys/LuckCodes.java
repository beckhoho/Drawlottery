package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_luck_codes")
public class LuckCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 幸运码状态
     */
    private Integer state;

    /**
     * 商品ID
     */
    @Column(name = "luck_code_template_id")
    private Long luckCodeTemplateId;

    /**
     * 用戶ID
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 訂單ID
     */
    @Column(name = "orders_id")
    private Long ordersId;

    /**
     * 购买时间
     */
    @Column(name = "buy_date")
    private Long buyDate;

    public Long getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Long buyDate) {
        this.buyDate = buyDate;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
     * 获取商品ID
     *
     * @return commodity_id - 商品ID
     */
    public Long getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品ID
     *
     * @param commodityId 商品ID
     */
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getLuckCodeTemplateId() {
        return luckCodeTemplateId;
    }

    public void setLuckCodeTemplateId(Long luckCodeTemplateId) {
        this.luckCodeTemplateId = luckCodeTemplateId;
    }
}