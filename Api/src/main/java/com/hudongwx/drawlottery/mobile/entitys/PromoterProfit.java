package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/13 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/13 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Table(name = "t_promoter_profit")
public class PromoterProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 推广员Id
     */
    @Column(name = "account_id")
    private String accountId;

    /**
     * 收入
     */
    private BigDecimal income;

    /**
     * 收入到账时间
     */
    @Column(name = "operate_time")
    private Long operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }
}
