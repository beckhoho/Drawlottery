package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

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
    IOdersService odersSer;
    @Test
    public void testAddOder() throws Exception {
        Date d = new Date();
        byte s = 1;
        Orders o = new Orders();
        o.setCommodityId(1l);
        o.setPayModeId(s);
        o.setPayState(s);
        o.setUserAccountId(1l);
        o.setSubmitDate(d);
        boolean b = odersSer.addOder(o);
        Assert.assertTrue(b);

        //测试完成    可用

    }
    @Test
    public void testSelectOder() throws Exception{

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long timeStart=sdf.parse("2016-12-22 20:06:02").getTime();
//        Date date = new Date(timeStart);

        List<Orders> oderses = odersSer.selectByUserAccount(0l);
        for (Orders o:oderses){
            System.out.println(o.getId()+"  ;"+o.getUserAccountId()+"  ;"+o.getCommodityId()+"  ;"+o.getSubmitDate());
        }
        Assert.assertNotNull(oderses);

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
        Orders oders = new Orders();
        oders.setId(1l);
        oders.setSubmitDate(new Date());
        oders.setUserAccountId(3l);
        boolean update = odersSer.update(oders);
        Assert.assertTrue(update);

        //测试成功  可用
    }

}