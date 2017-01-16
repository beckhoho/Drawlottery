package com.hudongwx.drawlottery.mobile.entitys;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/16 <br/>
 * <p>
 * 订单表单数据
 * <p>
 * @email 294786949@qq.co */
public class OrderFormData {

    private Orders order;

    private List<CommodityAmount> caList;

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<CommodityAmount> getCaList() {
        return caList;
    }

    public void setCaList(List<CommodityAmount> caList) {
        this.caList = caList;
    }
}
