package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/9 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/9 20:26　<br/>
 * <p>
 *          商品兑换方式对应表
 * <p>
 * @email 346905702@qq.com
 */
@Table(name = "t_commodity_exchange")
public class CommodityExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commodity_id")
    private Long commodityId;

    @Column(name = "exchange_way_id")
    private Integer exchangeWayId;

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

    public Integer getExchangeWayId() {
        return exchangeWayId;
    }

    public void setExchangeWayId(Integer exchangeWayId) {
        this.exchangeWayId = exchangeWayId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
