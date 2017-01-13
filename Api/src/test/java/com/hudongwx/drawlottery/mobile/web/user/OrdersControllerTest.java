package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/26 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/26 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class OrdersControllerTest extends TestBaseWeb {

    @Test
    public void testQueryOrderSuccess() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Orders orders = new Orders();
        orders.setPayModeId(1);
        orders.setPrice(10);
        orders.setSubmitDate(new Date().getTime());
        map.put("order", orders);
        List<CommodityAmount> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
        CommodityAmount ca = new CommodityAmount();
        ca.setCommodityId(46L);
        ca.setAmount(1);
        list.add(ca);
//        }
        map.put("ca", list);
        String s = "{\"ca\":\"[{\"commodityId\":4,\"amount\":1},{\"commodityId\":8,\"amount\":2}]}";
        System.out.println(JSON.toJSONString(map));
        post("/api/v1/user/orders/suc", JSON.toJSONString(map));
    }

    @Resource
    OrdersController ordersController;

    @Test
    public void testAddOrders() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Orders order = new Orders();
        order.setUserAccountId(10000L);
        order.setPrice(1);
        order.setPayModeId(1);
        order.setSubmitDate(new Date().getTime());
        map.put("order", order);
        List<CommodityAmount> list = new ArrayList<>();
        CommodityAmount ca = new CommodityAmount();
        ca.setAmount(1);
        ca.setCommodityId(4L);
        list.add(ca);
        map.put("ca", list);
        post("/api/v1/user/orders/sub", JSON.toJSONString(map));

    }

    @Test
    public void testDeleteOrder() throws Exception {

    }

    @Test
    public void testQueryOrder() throws Exception {

    }

    @Test
    public void testQueryAllUserOrders() throws Exception {

    }

    @Override
    public Object getController() {
        return ordersController;
    }
}