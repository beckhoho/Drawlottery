package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/16 20:33　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 卡号
     */
    @Column(name = "card_num")
    private String cardNum;

    /**
     * 卡密
     */
    private String password;

    /**
     * 运营商
     */
    private Integer corporation;

    /**
     * 面额
     */
    private Integer money;

    /**
     * 卡状态（1：已派发，0：未派发）
     */
    private Integer state;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
