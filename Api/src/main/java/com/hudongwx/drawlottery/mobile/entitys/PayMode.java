package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_pay_mode")
public class PayMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付方式1（1 是 || 0 否）
     */
    @Column(name = "pay_mode_1")
    private Byte payMode1;

    /**
     * 支付方式2（1 是 || 0 否）
     */
    @Column(name = "pay_mode_2")
    private Byte payMode2;

    /**
     * 支付方式3（1 是 || 0 否）
     */
    @Column(name = "pay_mode_3")
    private Byte payMode3;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

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
     * 获取支付方式1（1 是 || 0 否）
     *
     * @return pay_mode_1 - 支付方式1（1 是 || 0 否）
     */
    public Byte getPayMode1() {
        return payMode1;
    }

    /**
     * 设置支付方式1（1 是 || 0 否）
     *
     * @param payMode1 支付方式1（1 是 || 0 否）
     */
    public void setPayMode1(Byte payMode1) {
        this.payMode1 = payMode1;
    }

    /**
     * 获取支付方式2（1 是 || 0 否）
     *
     * @return pay_mode_2 - 支付方式2（1 是 || 0 否）
     */
    public Byte getPayMode2() {
        return payMode2;
    }

    /**
     * 设置支付方式2（1 是 || 0 否）
     *
     * @param payMode2 支付方式2（1 是 || 0 否）
     */
    public void setPayMode2(Byte payMode2) {
        this.payMode2 = payMode2;
    }

    /**
     * 获取支付方式3（1 是 || 0 否）
     *
     * @return pay_mode_3 - 支付方式3（1 是 || 0 否）
     */
    public Byte getPayMode3() {
        return payMode3;
    }

    /**
     * 设置支付方式3（1 是 || 0 否）
     *
     * @param payMode3 支付方式3（1 是 || 0 否）
     */
    public void setPayMode3(Byte payMode3) {
        this.payMode3 = payMode3;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}