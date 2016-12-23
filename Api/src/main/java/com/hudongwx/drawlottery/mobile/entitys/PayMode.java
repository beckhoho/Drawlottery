package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_pay_mode")
public class PayMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付方式名
     */
    @Column(name = "pay_name")
    private String payName;

    /**
     * 支付状态（1为可用，0为不可用）
     */
    @Column(name = "pay_state")
    private Integer payState;

    /**
     * 用户account_ID
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
     * 获取支付方式名
     *
     * @return pay_name - 支付方式名
     */
    public String getPayName() {
        return payName;
    }

    /**
     * 设置支付方式名
     *
     * @param payName 支付方式名
     */
    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    /**
     * 获取支付状态（1为可用，0为不可用）
     *
     * @return pay_state - 支付状态（1为可用，0为不可用）
     */
    public Integer getPayState() {
        return payState;
    }

    /**
     * 设置支付状态（1为可用，0为不可用）
     *
     * @param payState 支付状态（1为可用，0为不可用）
     */
    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    /**
     * 获取用户account_ID
     *
     * @return user_account_id - 用户account_ID
     */
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * 设置用户account_ID
     *
     * @param userAccountId 用户account_ID
     */
    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }
}