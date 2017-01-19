package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_orders")
public class Orders implements Serializable {

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
    private Long submitDate;

    /**
     * 点击使用的红包ID
     */
    @Column(name = "red_packet_id")
    private Long redPacketId;

    @Column(name = "address_ip")
    private String addressIp;

    @Column(name = "pay_state")
    private Integer payState;

    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

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
    public Long getSubmitDate() {
        return submitDate;
    }

    /**
     * 设置订单提交时间
     *
     * @param submitDate 订单提交时间
     */
    public void setSubmitDate(Long submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * 获取付款状态
     *
     * @return
     */
    public Integer getPayState() {
        return payState;
    }

    /**
     * 设置付款状态
     *
     * @param payState
     */
    public void setPayState(Integer payState) {
        this.payState = payState;
    }
}