package com.hudongwx.drawlottery.pojo;

import java.util.Date;
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

    @Column(name = "buy_date")
    private Date buyDate;

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
     * @return luck_code_id - 幸运号id
     */
    public Long getLuckCodeId() {
        return luckCodeId;
    }

    /**
     * 设置幸运号id
     *
     * @param luckCodeId 幸运号id
     */
    public void setLuckCodeId(Long luckCodeId) {
        this.luckCodeId = luckCodeId;
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

    /**
     * @return buy_date
     */
    public Date getBuyDate() {
        return buyDate;
    }

    /**
     * @param buyDate
     */
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}