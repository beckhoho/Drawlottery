package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_user_luck_codes")
public class UserLuckCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 幸运号id
     */
    @Column(name = "luck_code_id")
    private Long luckCodeId;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 用户id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 购买时间
     */
    @Column(name = "buy_date")
    private Long buyDate;

    /**
     * 订单ID
     */
    @Column(name = "orders_id")
    private Long ordersId;

    /**
     * IP地址
     */
    @Column(name = "address_ip")
    private String addressIp;


    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Long buyDate) {
        this.buyDate = buyDate;
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
     * 获取幸运号id
     *
     * @return lock_code_id - 幸运号id
     */
    public Long getLuckCodeId() {
        return luckCodeId;
    }

    /**
     * 设置幸运号id
     *
     * @param lockCodeId 幸运号id
     */
    public void setLuckCodeId(Long lockCodeId) {
        this.luckCodeId = lockCodeId;
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

    /**
     * 获取用户id
     *
     * @return user_account_id - 用户id
     */
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * 设置用户id
     *
     * @param userAccountId 用户id
     */
    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }
}