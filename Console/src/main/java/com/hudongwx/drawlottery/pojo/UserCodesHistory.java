package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;

@Table(name = "t_user_luck_codes_history")
public class UserCodesHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 幸运号id
     */
    @Column(name = "user_luck_code_id")
    private Long userLuckCodeId;

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
     * @return user_luck_code_id - 幸运号id
     */
    public Long getUserLuckCodeId() {
        return userLuckCodeId;
    }

    /**
     * 设置幸运号id
     *
     * @param userLuckCodeId 幸运号id
     */
    public void setUserLuckCodeId(Long userLuckCodeId) {
        this.userLuckCodeId = userLuckCodeId;
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