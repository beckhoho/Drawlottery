package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_pay_mode")
public class PayMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 支付方式1（1 是 || 0 否）
     */
    @Column(name = "pay_mode_1")
    private Integer payMode1;

    /**
     * 支付方式2（1 是 || 0 否）
     */
    @Column(name = "pay_mode_2")
    private Integer payMode2;

    /**
     * 支付方式3（1 是 || 0 否）
     */
    @Column(name = "pay_mode_3")
    private Integer payMode3;

    /**
     * 用户ID
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
     * 获取支付方式1（1 是 || 0 否）
     *
     * @return pay_mode_1 - 支付方式1（1 是 || 0 否）
     */
    public Integer getPayMode1() {
        return payMode1;
    }

    /**
     * 设置支付方式1（1 是 || 0 否）
     *
     * @param payMode1 支付方式1（1 是 || 0 否）
     */
    public void setPayMode1(Integer payMode1) {
        this.payMode1 = payMode1;
    }

    /**
     * 获取支付方式2（1 是 || 0 否）
     *
     * @return pay_mode_2 - 支付方式2（1 是 || 0 否）
     */
    public Integer getPayMode2() {
        return payMode2;
    }

    /**
     * 设置支付方式2（1 是 || 0 否）
     *
     * @param payMode2 支付方式2（1 是 || 0 否）
     */
    public void setPayMode2(Integer payMode2) {
        this.payMode2 = payMode2;
    }

    /**
     * 获取支付方式3（1 是 || 0 否）
     *
     * @return pay_mode_3 - 支付方式3（1 是 || 0 否）
     */
    public Integer getPayMode3() {
        return payMode3;
    }

    /**
     * 设置支付方式3（1 是 || 0 否）
     *
     * @param payMode3 支付方式3（1 是 || 0 否）
     */
    public void setPayMode3(Integer payMode3) {
        this.payMode3 = payMode3;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}