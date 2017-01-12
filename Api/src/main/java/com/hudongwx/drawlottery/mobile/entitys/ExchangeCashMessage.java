package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/11 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/11 20:12　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_exchange_cash_message")
public class ExchangeCashMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 用户真实姓名
     */
    @Column(name = "user_real_name")
    private String userRealName;

    /**
     * 第三方平台名
     */
    @Column(name = "exchange_terrace_name")
    private String exchangeTerraceName;
    /**
     * 第三方兑换平台
     */
    @Column(name = "terrace_account")
    private String terraceAccount;

    /**
     * 兑换金额
     */
    @Column(name = "exchange_amount")
    private Integer exchangeAmount;

    /**
     * 处理状态（1：已处理，0：未处理）
     */
    private Integer state;

    public String getExchangeTerraceName() {
        return exchangeTerraceName;
    }

    public void setExchangeTerraceName(String exchangeTerraceName) {
        this.exchangeTerraceName = exchangeTerraceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getTerraceAccount() {
        return terraceAccount;
    }

    public void setTerraceAccount(String terraceAccount) {
        this.terraceAccount = terraceAccount;
    }

    public Integer getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(Integer exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
