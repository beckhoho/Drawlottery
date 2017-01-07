package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户accuount_id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;


    /**
     * 价格
     */
    private Integer price;


    /**
     * 支付方式id
     */
    @Column(name = "pay_mode_id")
    private Integer payModeId;

    /**
     * 订单提交时间
     */
    @Column(name = "submit_date")
    private Date submitDate;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
     * 获取支付方式id
     *
     * @return pay_mode_id - 支付方式id
     */
    public Integer getPayModeId() {
        return payModeId;
    }

    /**
     * 设置支付方式id
     *
     * @param payModeId 支付方式id
     */
    public void setPayModeId(Integer payModeId) {
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