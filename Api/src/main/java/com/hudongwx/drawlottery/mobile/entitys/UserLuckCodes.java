package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_user_luck_codes")
public class UserLuckCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 幸运号id
     */
    @Column(name = "lock_code_id")
    private Integer lockCodeId;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取幸运号id
     *
     * @return lock_code_id - 幸运号id
     */
    public Integer getLockCodeId() {
        return lockCodeId;
    }

    /**
     * 设置幸运号id
     *
     * @param lockCodeId 幸运号id
     */
    public void setLockCodeId(Integer lockCodeId) {
        this.lockCodeId = lockCodeId;
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

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}