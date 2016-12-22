package com.hudongwx.drawlottery.mobile.entitys;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_red_packets")
public class RedPackets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 生效日期
     */
    @Column(name = "valid_date")
    private Date validDate;

    /**
     * 失效日期
     */
    @Column(name = "overdue_date")
    private Date overdueDate;

    /**
     * 红包名字
     */
    private String name;

    /**
     * 使用条件,订单高于该价格就可使用
     */
    @Column(name = "use_price")
    private BigDecimal usePrice;

    /**
     * 红包大小
     */
    private BigDecimal worth;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取生效日期
     *
     * @return valid_date - 生效日期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 设置生效日期
     *
     * @param validDate 生效日期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 获取失效日期
     *
     * @return overdue_date - 失效日期
     */
    public Date getOverdueDate() {
        return overdueDate;
    }

    /**
     * 设置失效日期
     *
     * @param overdueDate 失效日期
     */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }

    /**
     * 获取红包名字
     *
     * @return name - 红包名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置红包名字
     *
     * @param name 红包名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取使用条件,订单高于该价格就可使用
     *
     * @return use_price - 使用条件,订单高于该价格就可使用
     */
    public BigDecimal getUsePrice() {
        return usePrice;
    }

    /**
     * 设置使用条件,订单高于该价格就可使用
     *
     * @param usePrice 使用条件,订单高于该价格就可使用
     */
    public void setUsePrice(BigDecimal usePrice) {
        this.usePrice = usePrice;
    }

    /**
     * 获取红包大小
     *
     * @return worth - 红包大小
     */
    public BigDecimal getWorth() {
        return worth;
    }

    /**
     * 设置红包大小
     *
     * @param worth 红包大小
     */
    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }
}