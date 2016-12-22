package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_luck_codes")
public class LuckCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 幸运号
     */
    @Column(name = "lock_code")
    private Integer lockCode;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取幸运号
     *
     * @return lock_code - 幸运号
     */
    public Integer getLockCode() {
        return lockCode;
    }

    /**
     * 设置幸运号
     *
     * @param lockCode 幸运号
     */
    public void setLockCode(Integer lockCode) {
        this.lockCode = lockCode;
    }

    /**
     * 获取商品ID
     *
     * @return commodity_id - 商品ID
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品ID
     *
     * @param commodityId 商品ID
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
}