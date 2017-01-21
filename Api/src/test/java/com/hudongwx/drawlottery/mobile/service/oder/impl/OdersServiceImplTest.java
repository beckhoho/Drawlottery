package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/24 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/24 15:01　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class OdersServiceImplTest extends TestBaseMapper {
    @Autowired
    IOrdersService odersSer;
    @Test
    public void testAddOder() throws Exception {

        List<CommodityAmount> list = new ArrayList<>();
        CommodityAmount com = new CommodityAmount();
        com.setAmount(20);
        com.setCommodityId(49l);
        list.add(com);
        Orders o = new Orders();
        o.setUserAccountId(10000l);
        o.setPayModeId(1);
        o.setPrice(20);
        o.setSubmitDate(new Date().getTime());
        //boolean pay = odersSer.pay(10000l, o, list);
        //Assert.assertTrue(pay);
        //测试完成    可用

    }

    @Autowired
    OrdersMapper mapper;

    @Test
    public void testSelectOder() throws Exception{

        Orders orders = new Orders();
        orders.setUserAccountId(1000l);
        List<Orders> select = mapper.selectByUserAccount(1000l);
        for (Orders o : select){
            System.out.println(o.getSubmitDate());
        }

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(timeStart);
        /*List<Orders> oderses = odersSer.selectPageProfitByAccountId(1l);
        System.out.println(oderses.size());
        Assert.assertNotNull(oderses);*/

        //测试完成   可用；

    }

    @Test
    public void testDeleteOder() throws Exception {
        boolean b = odersSer.deleteOder(2L);
        Assert.assertTrue(b);

        //测试成功  可用
    }

    @Test
    public void testUpdate() throws Exception {
//        Map<String, Object> map = odersSer.selectUsableRedPackets(10000l, 1000);
//        System.out.println(map.get("remainder"));
//        System.out.println(map.get("useRedPackets"));
        // 测试成功  可用

    }

}