package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import org.testng.annotations.Test;

import java.util.Date;

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
    public void testAddOrders() throws Exception {
        Orders orders=new Orders();
        orders.setCommodityId(1000l);
        orders.setUserAccountId(10000l);
        orders.setPayModeId((byte) 1);
        orders.setSubmitDate(new Date());
        orders.setPayState((byte) 1);
        post("/user/orders/add", JSON.toJSONString(orders));
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
        return new OrdersController();
    }
}