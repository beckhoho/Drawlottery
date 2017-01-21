package com.hudongwx.drawlottery.mobile.service.alipay.impl;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/20 0020 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/20 0020　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public class AliPayServiceImplTest extends TestBaseMapper {

    @Autowired
    IAliPayService payService;

    /**
     * 创建临时订单
     * @throws Exception
     */
    @Test
    public void testCreateTemporaryOrder() throws Exception {

        OrderFormData data = new OrderFormData();

        List<CommodityAmount> amounts = new ArrayList<>();
        //商品
        CommodityAmount commodityAmount = new CommodityAmount();
        commodityAmount.setAmount(10);
        commodityAmount.setCommodityId(100L);
        amounts.add(commodityAmount);
        data.setCaList(amounts);

        Orders orders = new Orders();
        orders.setPrice(10);
        orders.setId(200l);
        orders.setUserAccountId(400l);
        data.setOrder(orders);

        JSONObject temporaryOrder = payService.createTemporaryOrder(data);
        String id = temporaryOrder.getString("id");
        String order = temporaryOrder.getString("order");

        System.out.println(id);
        System.out.println(order);

    }

    @Test
    public void testGetTemporaryOrder() throws Exception {

    }

    @Test
    public void testRemoveTemporaryOrder() throws Exception {

    }

    @Test
    public void testCreateSign() throws Exception {

    }

}