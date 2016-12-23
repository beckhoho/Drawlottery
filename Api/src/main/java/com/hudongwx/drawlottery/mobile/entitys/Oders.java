package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_oders")
public class Oders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 用户accuount_id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 是否已支付（1为已支付，0为未支付）
     */
    @Column(name = "pay_state")
    private Byte payState;

    /**
     * 支付方式id
     */
    @Column(name = "pay_mode_id")
    private Byte payModeId;

    /**
     * 订单提交时间
     */
    @Column(name = "submit_date")
    private Date submitDate;

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
     * 获取商品id
     *
     * @return commodity_id - 商品id
     */
    public Long getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品id
     *
     * @param commodityId 商品id
     */
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 获取用户accuount_id
     *
     * @return user_account_id - 用户accuount_id
     */
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * 设置用户accuount_id
     *
     * @param userAccountId 用户accuount_id
     */
    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    /**
     * 获取是否已支付（1为已支付，0为未支付）
     *
     * @return pay_state - 是否已支付（1为已支付，0为未支付）
     */
    public Byte getPayState() {
        return payState;
    }

    /**
     * 设置是否已支付（1为已支付，0为未支付）
     *
     * @param payState 是否已支付（1为已支付，0为未支付）
     */
    public void setPayState(Byte payState) {
        this.payState = payState;
    }

    /**
     * 获取支付方式id
     *
     * @return pay_mode_id - 支付方式id
     */
    public Byte getPayModeId() {
        return payModeId;
    }

    /**
     * 设置支付方式id
     *
     * @param payModeId 支付方式id
     */
    public void setPayModeId(Byte payModeId) {
        this.payModeId = payModeId;
    }

    /**
     * 获取订单提交时间
     *
     * @return submit_date - 订单提交时间
     */
    public Date getSubmitDate() {
        return submitDate;
    }

    /**
     * 设置订单提交时间
     *
     * @param submitDate 订单提交时间
     */
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}