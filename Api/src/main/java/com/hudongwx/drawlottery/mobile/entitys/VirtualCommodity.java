package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 21:51　<br/>
 * <p>
 * 虚拟商品
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_virtual_commodity")
public class VirtualCommodity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 卡号
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * 卡密
     */
    private String password;

    /**
     * 运营商
     */
    private String operator;

    /**
     * 价格面额
     */
    private Integer worth;

    /**
     * 使用状态
     */
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getWorth() {
        return worth;
    }

    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer State) {
        this.state = State;
    }
}
