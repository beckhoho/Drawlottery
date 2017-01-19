package com.hudongwx.drawlottery.mobile.entitys;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 * @author wu
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/16 <br/>
 * <p>
 * 订单表单数据
 * <p>
 * @email 294786949@qq.co */
public class OrderFormData implements Serializable {

    @NotNull(message = "订单创建错误")
    private Orders order;

    @Size(min = 1,message = "没有购买商品")
    @NotEmpty(message = "没有购买商品")
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
