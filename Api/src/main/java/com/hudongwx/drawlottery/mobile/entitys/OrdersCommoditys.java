package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 13:41　<br/>
 * <p>
 *          订单商品对应表实体类
 * <p>
 * @email 346905702@qq.com
 */
public class OrdersCommoditys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单ID
     */
    @Column(name = "orders_id")
    private Long ordersId;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 购买数量
     */
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
