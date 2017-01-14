package com.hudongwx.drawlottery.mobile.service.order.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by 11 on 2017/1/14.
 */
public class OrdersServiceImplTest extends TestBaseMapper {
    @Autowired
    IOrdersService service;
    @Test
    public void testPay() throws Exception {
        Long accountId = 10000L;
        Orders orders = new Orders();
        orders.setId(169L);
        orders.setUserAccountId(accountId);
        orders.setPayModeId(1);
        orders.setRedPacketId(4L);
        orders.setPrice(100);
        List<CommodityAmount> list = new ArrayList<>();
        CommodityAmount commodityAmount = new CommodityAmount();
        commodityAmount.setCommodityId(50L);
        commodityAmount.setAmount(100);
        list.add(commodityAmount);
        service.pay(accountId,orders,list);
    }

}