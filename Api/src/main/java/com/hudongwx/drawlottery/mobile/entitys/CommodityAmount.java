package com.hudongwx.drawlottery.mobile.entitys;

import java.io.Serializable;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 16:42　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityAmount implements Serializable{

    private Long commodityId; //商品id
    private Integer amount;  //商品数量

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
